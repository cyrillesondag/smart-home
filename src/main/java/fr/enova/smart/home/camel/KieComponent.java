package fr.enova.smart.home.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import java.util.Map;

public class KieComponent extends DefaultComponent {

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new KieEndpoint(uri, remaining, this);
        setProperties(endpoint, parameters);
        endpoint.configureProperties(parameters);
        return endpoint;
    }

    @Override
    protected void validateParameters(String uri, Map<String, Object> parameters, String optionPrefix) {
        // Don't validate so we can stub any URI
    }
}
