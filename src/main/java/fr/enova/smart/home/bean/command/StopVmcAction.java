package fr.enova.smart.home.bean.command;

public class StopVmcAction implements Action {

    @Override
    public String actionURI() {
        return "node?node_id=12&instance_id=2&cc_id=37&index=0&type=setvalue&value=255&apikey=590uQDc25Mutc43DR2ISsYGmClLH6nY7";
    }

}
