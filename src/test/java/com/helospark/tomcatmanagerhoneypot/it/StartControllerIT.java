package com.helospark.tomcatmanagerhoneypot.it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StartControllerIT extends AbstractTextControllerIT {
    @Test
    public void testStartApplicationControllerWithValidRequest() {
        // GIVEN

        // WHEN
        ResponseEntity<String> result = performAuthenticatedCall("start?path=/evil", HttpMethod.GET);

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is("OK - Started application at context path /evil"));
    }
}
