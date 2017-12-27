package fr.enova.smart.home.configuration;

import fr.enova.smart.home.bean.*;
import fr.enova.smart.home.exception.EventInstantiateException;
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

        rest("/push")
                .get("/sensorType/{sensorType}/commandId/{commandId}/humanCommand/{humanCommand}/value/{value}/room/{room}")
                .to("direct:mapper");

        from("direct:mapper")
                .routeId("mapper")
                .choice()
                .when(header("sensorType").contains(SensorTypeEnum.TEMPERATURE.getSensorType()))
                    .process(exchange -> {
                        Event<Double> event = EventFactory.getInstance()
                                .buildEvent(exchange, TemperatureEvent.class, Double.class);
                        exchange.getIn().setBody(event);
                    })
                    .to("direct:kie")
                .when(header("sensorType").contains(SensorTypeEnum.BRIGHTNESS.getSensorType()))
                    .process(exchange -> {
                        Event<Integer> event = EventFactory.getInstance()
                                .buildEvent(exchange, BrightnessEvent.class, Integer.class);
                        exchange.getIn().setBody(event);
                    })
                    .to("direct:kie")
                .when(header("sensorType").contains(SensorTypeEnum.PRESENCE.getSensorType()))
                    .process(exchange -> {
                        Event<Boolean> event = EventFactory.getInstance()
                                .buildEvent(exchange, PresenceEvent.class, Boolean.class);
                        exchange.getIn().setBody(event);
                    })
                    .to("direct:kie")
                .when(header("sensorType").contains(SensorTypeEnum.BATTERY.getSensorType()))
                    .process(exchange -> {
                        Event<Integer> event = EventFactory.getInstance()
                                .buildEvent(exchange, BatteryEvent.class, Integer.class);
                        exchange.getIn().setBody(event);
                    })
                    .to("direct:kie")
                .when(header("sensorType").contains(SensorTypeEnum.ANTI_SABOTAGE.getSensorType()))
                    .process(exchange -> {
                        Event<Boolean> event = EventFactory.getInstance()
                                .buildEvent(exchange, AntiSabotageEvent.class, Boolean.class);
                        exchange.getIn().setBody(event);
                    })
                    .to("direct:kie")
                .otherwise()
                    .log("Impossible de generer une event a partir de "
                            + header("CamelHttpUri")
                            + ". SensorType "
                            + header("sensorType") + " inconnu");

        from("direct:kie")
                .process(exchange -> System.out.println(exchange.getIn().getBody()))
                .to("kie:homeSession");

        from("kie:homeSession?channel=smartbox")
                .log("COOL DOWN T°C: " + bodyAs(PresenceEvent.class).toString());

        from("kie:homeSession?channel=warning")
                .log("WARNING T°C: " + bodyAs(PresenceEvent.class).toString());
    }
}
