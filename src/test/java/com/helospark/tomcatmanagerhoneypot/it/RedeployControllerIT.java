package com.helospark.tomcatmanagerhoneypot.it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RedeployControllerIT extends AbstractTextControllerIT {
    @Test
    public void testRedeployApplicationController() {
        // GIVEN

        // WHEN
        ResponseEntity<String> result = performAuthenticatedCall("undeploy?path=/evil", HttpMethod.GET);

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is("OK - Undeployed application at context path /evil"));
    }
}
