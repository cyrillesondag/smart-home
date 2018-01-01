package fr.enova.smart.home.brms;

import fr.enova.smart.home.bean.event.RoomEnum;
import fr.enova.smart.home.bean.event.SensorNameEnum;
import fr.enova.smart.home.bean.event.TemperatureEvent;
import fr.enova.smart.home.common.AbstractRulesCommonTest;
import org.junit.Test;
import org.kie.api.runtime.KieSession;

public class TestRules extends AbstractRulesCommonTest {

    @Test
    public void testHighVmcGame() throws Exception {

        KieSession kieSession = newKieSession("drls/smart-home.drl");

        TemperatureEvent temperatureEvent = new TemperatureEvent();
        temperatureEvent.setRoomName(RoomEnum.LIVING);
        temperatureEvent.setSensorName(SensorNameEnum.SENSOR_TEMPERATURE);
        temperatureEvent.setValue(new Double(19));

        kieSession.insert(temperatureEvent);

    }
}
