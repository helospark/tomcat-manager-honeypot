package com.helospark.tomcatmanagerhoneypot.it.text;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SessionInfoControllerIT extends AbstractTextControllerIT {
    @Test
    public void testSessionInfoController() {
        // GIVEN

        // WHEN
        ResponseEntity<String> result = performAuthenticatedCall("session?path=/evil", HttpMethod.GET);

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(
                "OK - Session information for application at context path [/evil]\nDefault maximum session inactive interval [30] minutes"));
    }
}
