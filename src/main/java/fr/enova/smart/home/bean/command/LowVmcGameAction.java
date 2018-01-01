package fr.enova.smart.home.bean.command;

import java.util.ArrayList;
import java.util.List;

public class LowVmcGameAction extends AbstractCommand{

    @Override
    public List<String> actionsURI() {
        List<String> actionsUri = new ArrayList<>(2);
        actionsUri.add("node?node_id=12&instance_id=2&cc_id=37&index=0&type=setvalue&value=255&apikey=590uQDc25Mutc43DR2ISsYGmClLH6nY7");
        actionsUri.add("node?node_id=12&instance_id=3&cc_id=37&index=0&type=setvalue&value=0&apikey=590uQDc25Mutc43DR2ISsYGmClLH6nY7");
        return actionsUri;
    }
}
