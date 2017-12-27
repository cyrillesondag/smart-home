package fr.enova.smart.home;

import fr.enova.smart.home.bean.Ack;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmartHomeTest {

    private static final String BASE_URL = "http://localhost:8888/smart/push/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CamelContext camelContext;

    @Test
    public void testSmartApi() throws Exception {

        // Wait for maximum 5s until the first order gets inserted and processed
//        NotifyBuilder notify = new NotifyBuilder(camelContext)
//                .fromRoute("mapper")
//                .whenDone(1)
////                .and()
////                .fromRoute("other-route")
////                .whenDone(1)
//                .create();
//        assertThat(notify.matches(5, TimeUnit.SECONDS)).isTrue();

        ResponseEntity<Ack> ackResponse =  restTemplate.getForEntity(BASE_URL + "sensorType/presence/commandId/6/humanCommand/%5Bliving%5D%5Bsensor.living%5D%5Bpresence%5D/value/1/room/living", Ack.class);
    }
}
