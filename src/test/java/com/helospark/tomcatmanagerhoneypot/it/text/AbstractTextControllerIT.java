package com.helospark.tomcatmanagerhoneypot.it.text;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractTextControllerIT {
    @LocalServerPort
    private int port;
    @Autowired
    protected TestRestTemplate testRestTemplate;

    protected ResponseEntity<String> performAuthenticatedCall(String uri, HttpMethod method) {
        return testRestTemplate.withBasicAuth("tomcat", "tomcat")
                .exchange(composeUrl(uri), method, null, String.class);
    }

    protected ResponseEntity<String> performNonauthenticatedCall(String uri, HttpMethod method) {
        return testRestTemplate
                .exchange(composeUrl(uri), method, null, String.class);
    }

    protected String composeUrl(String uri) {
        return "http://localhost:" + port + "/manager/text/" + uri;
    }
}
