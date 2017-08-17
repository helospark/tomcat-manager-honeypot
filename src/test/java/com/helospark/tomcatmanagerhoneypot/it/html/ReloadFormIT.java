package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ReloadFormIT extends AbstractUiIT {
    private WebElement reloadForm;

    @Before
    public void setUp() {
        super.init();
        performCall("/manager/html");
        reloadForm = findFormWithAction("/manager/html/reload");
    }

    @After
    public void tearDown() {
        super.destroy();
    }

    @Test
    public void testReloadShouldResultInExpectedMessage() {
        // GIVEN

        // WHEN
        reloadForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is("OK - Reloaded application at context path /"));
    }

}
