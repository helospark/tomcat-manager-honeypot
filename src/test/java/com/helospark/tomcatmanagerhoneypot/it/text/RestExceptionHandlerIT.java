package com.helospark.tomcatmanagerhoneypot.it.text;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.helospark.tomcatmanagerhoneypot.service.StopApplicationService;

public class RestExceptionHandlerIT extends AbstractTextControllerIT {
    @MockBean
    private StopApplicationService mockStopApplicationService;
    @MockBean
    private Logger logger;

    @Test
    public void testExceptionControllerOnUnexpectedException() {
        // GIVEN
        when(mockStopApplicationService.stopApplication("/")).thenThrow(new RuntimeException());

        // WHEN
        ResponseEntity<String> result = performAuthenticatedCall("stop?path=/", HttpMethod.GET);

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is("FAIL - Encountered exception"));
    }
}
