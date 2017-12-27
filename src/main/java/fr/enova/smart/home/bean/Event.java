package fr.enova.smart.home.bean;

public interface Event<T> {

    long getEventTimestamp();

    SensorNameEnum getSensorName();
    void setSensorName(SensorNameEnum sensorName);

    RoomEnum getRoom();
    void setRoomName(RoomEnum roomName);

    T getValue();
    void setValue(T value);

}
