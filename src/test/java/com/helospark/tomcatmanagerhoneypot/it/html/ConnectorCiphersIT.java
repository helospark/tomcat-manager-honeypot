package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ConnectorCiphersIT extends AbstractUiIT {
    private WebElement deployForm;

    @Before
    public void setUp() {
        super.init();
    }

    @After
    public void tearDown() {
        super.destroy();
    }

    @Test
    public void testSubmitShouldLoadNewPage() {
        // GIVEN
        performCall("/manager/html");
        deployForm = findFormWithAction("/manager/html/sslConnectorCiphers");

        // WHEN
        deployForm.submit();

        // THEN
        WebElement text = webDriver.findElement(By.xpath("/html/body/h1"));
        assertThat(text.getText(), is("Configured ciphers per Connector"));
    }

    @Test
    public void testReturnToMainPageShouldRedirectToMainPage() {
        // GIVEN
        performCall("/manager/html/sslConnectorCiphers");
        deployForm = findFormWithAction("/manager/html");

        // WHEN
        deployForm.submit();

        // THEN
        assertPageTitleIs("Tomcat Web Application Manager");
    }

}
