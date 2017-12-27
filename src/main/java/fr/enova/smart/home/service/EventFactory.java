package fr.enova.smart.home.service;

import fr.enova.smart.home.bean.event.*;
import fr.enova.smart.home.exception.EventInstantiateException;
import org.apache.camel.Exchange;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static fr.enova.smart.home.bean.event.SensorTypeEnum.*;

public class EventFactory {

    private EventFactory() {
    }

    private static class EventFactoryHolder {
        private final static EventFactory instance = new EventFactory();
    }

    public static EventFactory getInstance() {
        return EventFactoryHolder.instance;
    }

    public Event buildEvent(Exchange exchange) throws EventInstantiateException {
        if (exchange.getIn().getHeader("sensorType", String.class).contains(TEMPERATURE.getSensorType())) {
            return buildEvent(exchange, TemperatureEvent.class, Double.class);

        } else if (exchange.getIn().getHeader("sensorType", String.class).contains(PRESENCE.getSensorType())) {
            return buildEvent(exchange, PresenceEvent.class, Boolean.class);

        } else if (exchange.getIn().getHeader("sensorType", String.class).contains(BRIGHTNESS.getSensorType())) {
            return buildEvent(exchange, BrightnessEvent.class, Integer.class);

        } else if (exchange.getIn().getHeader("sensorType", String.class).contains(ANTI_SABOTAGE.getSensorType())) {
            return buildEvent(exchange, AntiSabotageEvent.class, Boolean.class);

        } else if (exchange.getIn().getHeader("sensorType", String.class).contains(BATTERY.getSensorType())) {
            return buildEvent(exchange, BatteryEvent.class, Integer.class);

        } else {
            throw new EventInstantiateException("Unable to instanciate event for sensorType "
                    + exchange.getIn().getHeader("sensorType"));
        }
    }

    public <T> Event<T> buildEvent(Exchange exchange, Class<? extends Event> eventClass, Class<T> valueClass)
            throws EventInstantiateException {
        T value = exchange.getIn().getHeader("value", valueClass);
        String roomName = exchange.getIn().getHeader("room", String.class);
        String humanCommand = exchange.getIn().getHeader("humanCommand", String.class);
        SensorNameEnum sensorNameEnum = SensorNameEnum.extractSensorName(doubleUrlDecoding(humanCommand));
        RoomEnum roomEnum = RoomEnum.extractRoomName(roomName);

        return instanciateEvent(eventClass, value, sensorNameEnum, roomEnum);
    }

    private <T> Event<T> instanciateEvent(Class<? extends Event> eventClass, T value,
                                          SensorNameEnum sensorNameEnum, RoomEnum roomEnum)
            throws EventInstantiateException {
        try {
            Event<T> event = eventClass.newInstance();
            event.setRoomName(roomEnum);
            event.setSensorName(sensorNameEnum);
            event.setValue(value);
            return event;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new EventInstantiateException("Unable to instanciate event for class "
                    + eventClass.getName(), e);
        }
    }

    private String doubleUrlDecoding(String url) throws EventInstantiateException {
        try {
            return URLDecoder.decode(URLDecoder.decode(url, "UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new EventInstantiateException("Impossible de decoder " + url, e);
        }
    }

}
