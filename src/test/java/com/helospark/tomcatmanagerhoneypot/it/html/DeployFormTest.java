package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DeployFormTest extends AbstractUiIT {
    private WebElement deployForm;

    @Before
    public void setUp() {
        super.init();
        performCall("/manager/html");
        deployForm = findFormWithAction("/manager/html/deploy");
    }

    @After
    public void tearDown() {
        super.destroy();
    }

    @Test
    public void testSubmitEmptyDeployFormShouldResultInFailMessage() {
        // GIVEN

        // WHEN
        deployForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertTrue(error.getText().matches("FAIL - Invalid .* \\[\\] was specified"));
    }

    @Test
    public void testSubmitFilledFormShouldResultInOkMessage() {
        fillFormToValidValue();

        // WHEN
        deployForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is("OK - Deployed application at context path /asd"));
    }

    @Test
    public void testPressingSubmitButtonShouldAlsoSubmitForm() {
        // GIVEN
        fillFormToValidValue();

        // WHEN
        deployForm.findElement(By.xpath(".//input[@type='submit']")).click();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is("OK - Deployed application at context path /asd"));
    }

    private void fillFormToValidValue() {
        deployForm.findElement(By.name("deployPath")).sendKeys("asd");
        deployForm.findElement(By.name("deployConfig")).sendKeys("something");
        deployForm.findElement(By.name("deployWar")).sendKeys("/asd");
    }
}
