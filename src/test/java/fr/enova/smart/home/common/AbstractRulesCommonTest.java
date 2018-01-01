package fr.enova.smart.home.common;

import fr.enova.smart.home.brms.DebugDroolsAgendaEventListener;
import fr.enova.smart.home.brms.DebugDroolsRuleRuntimeEventListener;
import fr.enova.smart.home.brms.RhsConsequenceExceptionHandler;
import org.drools.core.ClockType;
import org.drools.core.RuleBaseConfiguration;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import static fr.enova.smart.home.brms.BrmsConstants.COMMAND_CHANNEL_NAME;

public abstract class AbstractRulesCommonTest {

    protected KieSession newKieSession(String drl) throws NoSuchFieldException, IllegalAccessException {
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();
        Resource res = ks.getResources().newClassPathResource(drl);
        kfs.write(res);

        KieBuilder kb = ks.newKieBuilder(kfs);
        kb.buildAll();
        if (kb.getResults().hasMessages()) {
            System.err.println("!!! Problem: " + kb.getResults().toString());
        }

        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());

        RuleBaseConfiguration kBaseConf = new RuleBaseConfiguration();
        kBaseConf.setEventProcessingMode(EventProcessingOption.STREAM);
        kBaseConf.setConsequenceExceptionHandler(RhsConsequenceExceptionHandler.class.getName());
        kBaseConf.setAssertBehaviour(RuleBaseConfiguration.AssertBehaviour.EQUALITY);

        KieBase kbase = kContainer.newKieBase(kBaseConf);
        KieSessionConfiguration ksessionConfig = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        ksessionConfig.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession ksession = kbase.newKieSession(ksessionConfig, null);

        ksession.addEventListener(new DebugDroolsAgendaEventListener("TEST"));
        ksession.addEventListener(new DebugDroolsRuleRuntimeEventListener("TEST"));

        ksession.registerChannel(COMMAND_CHANNEL_NAME, new SimpleCommandChannel());

        return ksession;
    }

}
