package fr.enova.smart.home.bean.command;

import fr.enova.smart.home.bean.event.RoomEnum;
import fr.enova.smart.home.bean.event.SensorNameEnum;

import java.util.List;

public interface Command {

    List<String> actionsURI();

    public long getEventTimestamp();
    public void setEventTimestamp(long eventTimestamp);

    public SensorNameEnum getSensorName();
    public void setSensorName(SensorNameEnum sensorName);

    public RoomEnum getRoomName();
    public void setRoomName(RoomEnum roomName);

}
