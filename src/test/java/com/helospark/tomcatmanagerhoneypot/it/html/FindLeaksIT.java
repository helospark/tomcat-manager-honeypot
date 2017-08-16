package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class FindLeaksIT extends AbstractUiIT {
    private WebElement deployForm;

    @Before
    public void setUp() {
        super.init();
        performCall("/manager/html");
        deployForm = findFormWithAction("/manager/html/findleaks");
    }

    @After
    public void tearDown() {
        super.destroy();
    }

    @Test
    public void testSubmitShouldResultInExpectedMessage() {
        // GIVEN

        // WHEN
        deployForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(),
                is("No web applications appear to have triggered a memory leak on stop, reload or undeploy."));
    }

}
