package com.helospark.tomcatmanagerhoneypot.it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServerInfoControllerIT extends AbstractTextControllerIT {
    @Test
    public void testServerInfoController() {
        // GIVEN

        // WHEN
        ResponseEntity<String> result = performAuthenticatedCall("serverinfo", HttpMethod.GET);

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(
                "OK - Server info\nTomcat Version: Apache Tomcat/9.0.0.M26\nOS Name: Linux\nOS Version: 4.8.0-56-generic\nOS Architecture: amd64\nJVM Version: 9+181\nJVM Vendor: Oracle Corporation"));
    }
}
