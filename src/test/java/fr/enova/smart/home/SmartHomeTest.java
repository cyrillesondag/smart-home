package fr.enova.smart.home;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.enova.smart.home.bean.RootObject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SmartHomeTest {

    private static final String BASE_URL = "http://localhost:8888/smart/push/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockRestServiceServer server;

    @Before
    public void setUp() throws Exception {
        RootObject rootObject = new RootObject();
        rootObject.setState("ok");
        String rootObjectString = objectMapper.writeValueAsString(rootObject);

//        server = MockRestServiceServer.createServer(restTemplate.getRestTemplate());
//        server.expect(requestTo("/node"))
//                .andRespond(withSuccess(rootObjectString, MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSmartApi() throws Exception {

        //Wait for maximum 5s until the first order gets inserted and processed
        NotifyBuilder notify = new NotifyBuilder(camelContext)
                .fromRoute("mapper")
                .whenDone(1)
//                .and()
//                .fromRoute("other-route")
//                .whenDone(1)
                .create();

        ResponseEntity<String> response =  restTemplate.getForEntity(BASE_URL + "sensorType/temperature/commandId/6/humanCommand/%5Bliving%5D%5Bsensor.living%5D%5Bpresence%5D/value/23/room/living", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("OK");
        restTemplate.getForEntity(BASE_URL + "sensorType/temperature/commandId/6/humanCommand/%5Bliving%5D%5Bsensor.living%5D%5Bpresence%5D/value/20/room/living", String.class);

        assertThat(notify.matches(1000, TimeUnit.MILLISECONDS)).isTrue();

    }
}
