package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExpireFormIT extends AbstractUiIT {
    private WebElement expireForm;

    @Before
    public void setUp() {
        super.init();
        performCall("/manager/html");
        expireForm = findFormWithAction("/manager/html/expire");
    }

    @After
    public void tearDown() {
        super.destroy();
    }

    @Test
    public void testExpireWithDefaultTimeShouldResultInExpectedMessage() {
        // GIVEN

        // WHEN
        expireForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is(
                "OK - Session information for application at context path [/]\nDefault maximum session inactive interval [30] minutes\n[>30] minutes: [0] sessions were expired"));
    }

    @Test
    public void testExpireWithChangedTimeShouldResultInExpectedMessage() {
        // GIVEN
        setIdleTo("10");

        // WHEN
        expireForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is(
                "OK - Session information for application at context path [/]\nDefault maximum session inactive interval [10] minutes\n[>10] minutes: [0] sessions were expired"));
    }

    @Test
    public void testExpireWithInvalidInputShouldResultIsErrorMessage() {
        // GIVEN
        setIdleTo("asd");

        // WHEN
        expireForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is("FAIL - Invalid idle [asd] was specified"));
    }

    @Test
    public void testExpireWithEmptyInputShouldResultIsErrorMessage() {
        // GIVEN
        setIdleTo("");

        // WHEN
        expireForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is("FAIL - Invalid idle [] was specified"));
    }

    private void setIdleTo(String idleValue) {
        WebElement idleInput = expireForm.findElement(By.name("idle"));
        idleInput.clear();
        idleInput.sendKeys(idleValue);
    }

}
