package fr.enova.smart.home.camel;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.kie.api.runtime.KieSession;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class KieEndpoint extends DefaultEndpoint {

    public static final String CHANNEL = "channel";
    private KieSession kieSession;

    private String name;
    private String channel;

    public KieEndpoint(String endpointUri, String remaining, KieComponent component) throws URISyntaxException, MalformedURLException {
        super(endpointUri, component);
        this.name = remaining;
        this.kieSession = component.getCamelContext().getRegistry().lookupByNameAndType(name, KieSession.class);
    }

    @Override
    public Producer createProducer() throws Exception {
        return new KieProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        Consumer answer = new KieConsumer(this, processor);
        configureConsumer(answer);
        return answer;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public KieSession getKieSession() {
        return kieSession;
    }

}