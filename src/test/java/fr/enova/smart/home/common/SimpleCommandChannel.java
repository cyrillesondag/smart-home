package fr.enova.smart.home.common;

import fr.enova.smart.home.bean.command.Command;
import org.kie.api.runtime.Channel;

import java.util.ArrayList;
import java.util.List;

public class SimpleCommandChannel implements Channel {

    private List<Command> commands = new ArrayList<>();

    @Override
    public void send(Object object) {
        if (object instanceof Command) {
            commands.add((Command) object);
        } else {
            throw new IllegalArgumentException("Unable to send other objet than Command into this channel");
        }
    }

    public List<Command> getCommands() {
        return commands;
    }
}
