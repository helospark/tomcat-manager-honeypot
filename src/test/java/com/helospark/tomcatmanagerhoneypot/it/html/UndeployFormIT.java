package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class UndeployFormIT extends AbstractUiIT {
    private WebElement undeployForm;

    @Before
    public void setUp() {
        super.init();
        performCall("/manager/html");
        undeployForm = findFormWithAction("/manager/html/undeploy");
    }

    @After
    public void tearDown() {
        super.destroy();
    }

    @Test
    public void testUndeployShouldResultInExpectedMessage() {
        // GIVEN

        // WHEN
        undeployForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is("OK - Undeployed application at context path /"));
    }

}
