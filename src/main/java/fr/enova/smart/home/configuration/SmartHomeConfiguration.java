package fr.enova.smart.home.configuration;

import fr.enova.smart.home.camel.KieComponent;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.event.rule.DefaultRuleRuntimeEventListener;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SmartHomeConfiguration extends CamelConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(SmartHomeConfiguration.class);

	@PostConstruct
	public void initCamelContext() throws Exception {
		camelContext().addComponent("kie", new KieComponent());
	}

	@Bean
	public KieSession homeSession() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();

		// Ajout d'un listener
		kSession.addEventListener(new DefaultAgendaEventListener() {
			@Override
			public void afterMatchFired(AfterMatchFiredEvent event) {
				LOG.info("Rule fire: " + event.getMatch().getRule().getName());
			}
		});

		kSession.addEventListener(new DefaultRuleRuntimeEventListener() {
			@Override
			public void objectInserted(ObjectInsertedEvent event) {
				LOG.info("Insert event: " + event.getObject());
			}
		});

		return kSession;
	}

}
