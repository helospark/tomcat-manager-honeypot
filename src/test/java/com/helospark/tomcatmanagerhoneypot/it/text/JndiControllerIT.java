package com.helospark.tomcatmanagerhoneypot.it.text;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JndiControllerIT extends AbstractTextControllerIT {
    @Test
    public void testJndiResourcesController() {
        // GIVEN

        // WHEN
        ResponseEntity<String> result = performAuthenticatedCall("resources?type=database", HttpMethod.GET);

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(
                "OK - Listed global resources of database types\nUserDatabase:org.apache.catalina.users.MemoryUserDatabase"));
    }
}
