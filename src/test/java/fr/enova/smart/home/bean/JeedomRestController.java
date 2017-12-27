package fr.enova.smart.home.bean;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

@RestController
public class JeedomRestController {

    public static final String FILE_NAME = "bean/jeedom_response_1.json";

    @GetMapping(value = "/node")
    public String node() throws Exception {
        InputStream inputStream = JeedomRestController.class.getResourceAsStream(FILE_NAME);
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, Charset.defaultCharset());
        return writer.toString();
    }

}
