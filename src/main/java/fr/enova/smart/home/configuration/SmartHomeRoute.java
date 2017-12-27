package fr.enova.smart.home.configuration;

import fr.enova.smart.home.bean.event.PresenceEvent;
import fr.enova.smart.home.exception.EventInstantiateException;
import fr.enova.smart.home.service.EventFactory;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmartHomeRoute extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(SmartHomeRoute.class);

    @Override
    public void configure() throws Exception {

        onException(EventInstantiateException.class)
                .log(LoggingLevel.ERROR, LOG, "${exception.message}")
                .continued(true);

        restConfiguration()
                .contextPath("/smart").apiContextPath("/push-api")
                .apiProperty("api.title", "Open ZWave push REST API")
                .apiProperty("api.version", "1.0")
                .apiContextRouteId("doc-api")
                .component("restlet").host("localhost")
                .port(8888);

        rest("/push").description("Jeedom push API")
                .consumes("application/json").produces("application/json")
                .get("/sensorType/{sensorType}/commandId/{commandId}/humanCommand/{humanCommand}/value/{value}/room/{room}")
                .responseMessage().code(200).endResponseMessage()
                .route()
                .inOnly("seda:mapper")
                .setBody(constant("OK"));


        from("seda:mapper")
                .routeId("mapper")
                .setExchangePattern(ExchangePattern.InOnly)
                .process(exchange -> exchange.getIn().setBody(EventFactory.getInstance().buildEvent(exchange)))
                .to("kie:homeSession");

        from("kie:homeSession?channel=command")
                .log("COOL DOWN T°C: " + bodyAs(PresenceEvent.class).toString());

        from("kie:homeSession?channel=warning")
                .log("WARNING T°C: " + bodyAs(PresenceEvent.class).toString());
    }
}
