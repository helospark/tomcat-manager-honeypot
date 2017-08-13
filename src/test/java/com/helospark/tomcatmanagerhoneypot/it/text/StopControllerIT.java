package com.helospark.tomcatmanagerhoneypot.it.text;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StopControllerIT extends AbstractTextControllerIT {
    @Test
    public void testStopApplicationController() {
        // GIVEN

        // WHEN
        ResponseEntity<String> result = performAuthenticatedCall("stop?path=/evil", HttpMethod.GET);

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is("OK - Stopped application at context path /evil"));
    }

}
