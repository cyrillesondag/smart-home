package fr.enova.smart.home.bean;

import java.util.EnumSet;

public enum RoomEnum {

    GAME,
    LIVING,
    KITCHEN,
    PARENT;

    public String getRoomName() {
        return name().toLowerCase();
    }

    public static RoomEnum extractRoomName(String roomName) {
        return EnumSet.allOf(RoomEnum.class).stream()
                .filter(roomEnum -> roomEnum.getRoomName().equals(roomName)).findFirst().orElse(null);
    }

}
