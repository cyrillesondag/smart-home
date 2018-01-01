package fr.enova.smart.home.bean.command;

import fr.enova.smart.home.bean.event.RoomEnum;
import fr.enova.smart.home.bean.event.SensorNameEnum;

public abstract class AbstractCommand implements Command {

    protected long eventTimestamp = System.currentTimeMillis();
    protected SensorNameEnum sensorName;
    protected RoomEnum roomName;

    public long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public SensorNameEnum getSensorName() {
        return sensorName;
    }

    public void setSensorName(SensorNameEnum sensorName) {
        this.sensorName = sensorName;
    }

    public RoomEnum getRoomName() {
        return roomName;
    }

    public void setRoomName(RoomEnum roomName) {
        this.roomName = roomName;
    }
}
