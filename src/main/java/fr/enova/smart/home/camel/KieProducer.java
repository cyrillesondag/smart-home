package fr.enova.smart.home.camel;

import org.apache.camel.AsyncCallback;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultAsyncProducer;
import org.kie.api.runtime.KieSession;

public class KieProducer extends DefaultAsyncProducer {

    private final KieEndpoint endpoint;

    public KieProducer(KieEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        insertAndFire(exchange);
    }

    public boolean process(Exchange exchange, AsyncCallback callback) {
        insertAndFire(exchange);
        callback.done(true);
        return true;
    }

    private void insertAndFire(Exchange exchange) {
        KieSession kieSession = endpoint.getKieSession();
        kieSession.insert(exchange.getIn().getBody());
        kieSession.fireAllRules();
    }
}
