package fr.enova.smart.home.bean;

import fr.enova.smart.home.exception.EventInstantiateException;
import org.apache.camel.Exchange;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class EventFactory {

    private EventFactory() {
    }

    private static class EventFactoryHolder {
        private final static EventFactory instance = new EventFactory();
    }

    public static EventFactory getInstance() {
        return EventFactoryHolder.instance;
    }

    public <T> Event<T> buildEvent(Exchange exchange, Class<? extends Event> eventClass, Class<T> valueClass) throws EventInstantiateException {
        T value = exchange.getIn().getHeader("value", valueClass);
        String roomName = exchange.getIn().getHeader("room", String.class);
        String humanCommand = exchange.getIn().getHeader("humanCommand", String.class);
        SensorNameEnum sensorNameEnum = SensorNameEnum.extractSensorName(doubleUrlDecoding(humanCommand));
        RoomEnum roomEnum = RoomEnum.extractRoomName(roomName);

        return instanciateEvent(eventClass, value, sensorNameEnum, roomEnum);
    }

    private <T> Event<T> instanciateEvent(Class<? extends Event> eventClass, T value, SensorNameEnum sensorNameEnum, RoomEnum roomEnum) throws EventInstantiateException {
        try {
            Event<T> event = eventClass.newInstance();
            event.setRoomName(roomEnum);
            event.setSensorName(sensorNameEnum);
            event.setValue(value);
            return event;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new EventInstantiateException("", e);
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
