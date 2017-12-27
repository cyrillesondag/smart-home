package fr.enova.smart.home.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.kie.api.runtime.Channel;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KieConsumer extends DefaultConsumer {

    private static final transient Logger LOG = LoggerFactory.getLogger(KieConsumer.class);

    private KieEndpoint ke;
    private KieSession kieSession;
    private Channel channel = new KSessionChannel();

    public KieConsumer(Endpoint endpoint, Processor processor) {
        super(endpoint, processor);
        ke = (KieEndpoint) endpoint;
        kieSession = ke.getKieSession();
    }

    @Override
    protected void doStop() throws Exception {
        LOG.info("Unregister kie channel {}", ke.getChannel());
        kieSession.unregisterChannel(ke.getChannel());
        super.doStop();
    }

    @Override
    protected void doStart() throws Exception {
        LOG.info("Register kie channel {}", ke.getChannel());
        kieSession.registerChannel(ke.getChannel(), channel);
        super.doStart();
    }

    private class KSessionChannel implements Channel {
        public void send(Object pojo) {
            Exchange exchange = ke.createExchange();
            exchange.getIn().setBody(pojo);
            try {
                getProcessor().process(exchange);
            } catch (Exception e) {
                handleException(e);
            }
        }
    }
}
