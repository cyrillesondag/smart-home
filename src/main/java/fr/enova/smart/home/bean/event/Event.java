package fr.enova.smart.home.bean.event;

public interface Event<T> {

    long getEventTimestamp();

    SensorNameEnum getSensorName();
    void setSensorName(SensorNameEnum sensorName);

    RoomEnum getRoomName();
    void setRoomName(RoomEnum roomName);

    T getValue();
    void setValue(T value);

}
