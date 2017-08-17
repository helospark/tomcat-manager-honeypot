package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.helospark.tomcatmanagerhoneypot.service.StopApplicationService;

public class HtmlExceptionHandlerFormIT extends AbstractUiIT {
    private WebElement stopForm;
    @MockBean
    private StopApplicationService mockStopApplicationService;
    @MockBean
    private Logger logger;

    @Before
    public void setUp() {
        super.init();
        performCall("/manager/html");
        stopForm = findFormWithAction("/manager/html/stop");
        when(mockStopApplicationService.stopApplication("/")).thenThrow(new RuntimeException());
    }

    @After
    public void tearDown() {
        super.destroy();
    }

    @Test
    public void testStopShouldResultInExpectedMessage() {
        // GIVEN

        // WHEN
        stopForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is("FAIL - Encountered exception"));
    }

}
