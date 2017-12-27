package fr.enova.smart.home.bean;

public enum SensorTypeEnum {

    TEMPERATURE("temperature"),
    PRESENCE("presence"),
    BRIGHTNESS("brightness"),
    ANTI_SABOTAGE("antisabotage"),
    BATTERY("battery");

    private String sensorType;

    SensorTypeEnum(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorType() {
        return sensorType;
    }
}
