package com.helospark.tomcatmanagerhoneypot.it.text;

import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;

public class LoggingIT extends AbstractTextControllerIT {
    @MockBean
    private Logger logger;

    @Test
    public void testJndiResourcesController() {
        // GIVEN

        // WHEN
        performAuthenticatedCall("resources?type=database", HttpMethod.GET);

        // THEN
        verify(logger).info(matches(
                "Starting to process request Before request \\[uri=/manager/text/resources\\?type=database;client=127\\.0\\.0\\.1;.*"));
        verify(logger).info("Finished processing request");
    }

}
