package fr.enova.smart.home.bean;

public abstract class AbstractEvent<T> implements Event<T> {

    protected long eventTimestamp = System.currentTimeMillis();
    protected SensorNameEnum sensorName;
    protected RoomEnum roomEnum;
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
    public RoomEnum getRoom() {
        return roomEnum;
    }

    @Override
    public void setRoomName(RoomEnum roomName) {
        this.roomEnum = roomName;
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
        if (roomEnum != that.roomEnum) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = sensorName != null ? sensorName.hashCode() : 0;
        result = 31 * result + (roomEnum != null ? roomEnum.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "eventTimestamp=" + eventTimestamp +
                ", sensorName=" + sensorName +
                ", roomEnum=" + roomEnum +
                ", value=" + value +
                '}';
    }
}
