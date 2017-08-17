package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class StopFormIT extends AbstractUiIT {
    private WebElement stopForm;

    @Before
    public void setUp() {
        super.init();
        performCall("/manager/html");
        stopForm = findFormWithAction("/manager/html/stop");
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
        assertThat(error.getText(), is("OK - Stopped application at context path /"));
    }

}
