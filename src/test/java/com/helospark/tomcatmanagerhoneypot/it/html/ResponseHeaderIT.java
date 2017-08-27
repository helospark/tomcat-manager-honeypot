package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class ResponseHeaderIT {
    @LocalServerPort
    private int port;
    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Test
    public void testHeadersMatchRealTomcatManagerHeaders() {
        // GIVEN

        // WHEN
        ResponseEntity<String> result = performAuthenticatedCall();

        // THEN
        HttpHeaders headers = result.getHeaders();
        assertThat(headers.get("Cache-Control").get(0), is("private"));
        assertThat(headers.get("Content-Type").get(0), is("text/html;charset=UTF-8"));
        assertNotNull(headers.get("Date").get(0));
        assertNotNull(headers.get("Content-Language").get(0));
        assertThat(headers.get("Expires").get(0), is("Thu, 01 Jan 1970 01:00:00 CET"));
        assertThat(headers.get("Server").get(0), is("Apache-Coyote/1.1"));
        assertTrue(headers.get("Set-Cookie").get(0).matches("JSESSIONID=.*?; Path=/manager; HttpOnly"));
        assertThat(headers.get("Transfer-Encoding").get(0), is("chunked"));
        assertThat(headers.size(), is(8));
    }

    protected ResponseEntity<String> performAuthenticatedCall() {
        return testRestTemplate.withBasicAuth("tomcat", "tomcat")
                .exchange(composeUrl(), HttpMethod.GET, null, String.class);
    }

    protected String composeUrl() {
        return "http://localhost:" + port + "/manager/html";
    }
}
