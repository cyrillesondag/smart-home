package fr.enova.smart.home.bean.event;

public abstract class AbstractEvent<T> implements Event<T> {

    protected long eventTimestamp = System.currentTimeMillis();
    protected SensorNameEnum sensorName;
    protected RoomEnum roomName;
    protected T value;

    @Override
    public long getEventTimestamp() {
        return eventTimestamp;
    }

    @Override
    public SensorNameEnum getSensorName() {
        return sensorName;
    }

    @Override
    public void setSensorName(SensorNameEnum sensorName) {
        this.sensorName = sensorName;
    }

    @Override
    public RoomEnum getRoomName() {
        return roomName;
    }

    @Override
    public void setRoomName(RoomEnum roomName) {
        this.roomName = roomName;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEvent)) return false;

        AbstractEvent<?> that = (AbstractEvent<?>) o;

        if (sensorName != that.sensorName) return false;
        if (roomName != that.roomName) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = sensorName != null ? sensorName.hashCode() : 0;
        result = 31 * result + (roomName != null ? roomName.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "eventTimestamp=" + eventTimestamp +
                ", sensorName=" + sensorName +
                ", roomName=" + roomName +
                ", value=" + value +
                '}';
    }
}
