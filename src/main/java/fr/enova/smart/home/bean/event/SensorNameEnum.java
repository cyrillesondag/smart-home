package fr.enova.smart.home.bean.event;

import java.util.EnumSet;

public enum SensorNameEnum {

    SENSOR_LIVING("sensor.living"),
    SENSOR_GAME("sensor.game"),
    SENSOR_TEMPERATURE("sensor.temperature"),
    SWITCH_VMC_GAME("switch.vmc.game");

    private String sensorName;

    SensorNameEnum(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public static SensorNameEnum extractSensorName(String humanCommand) {
        humanCommand = humanCommand.replaceAll("\\[", "");
        String[] commands = humanCommand.split("\\]");
        return EnumSet.allOf(SensorNameEnum.class).stream()
                .filter(sensorName -> sensorName.getSensorName().equals(commands[1])).findFirst().orElse(null);
    }

}
