package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public abstract class AbstractUiIT {
    protected WebDriver webDriver;

    @LocalServerPort
    private int port;

    public void init() {
        webDriver = new HtmlUnitDriver();
    }

    public void destroy() {
        webDriver.close();
    }

    protected WebElement findFormWithAction(String action) {
        return webDriver.findElement(By.xpath(
                "/html/body//form[starts-with(@action, '" + action + "')]"));
    }

    protected void performCall(String url) {
        webDriver.get("http://tomcat:tomcat@localhost:" + port + url);
    }

    protected WebElement getStatusMessage() {
        return webDriver.findElement(By.xpath("/html/body/table[3]/tbody/tr/td[2]"));
    }

    protected void assertPageTitleIs(String expectedTitle) {
        WebElement text = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/font"));
        String title = text.getText();
        assertThat(title, is(expectedTitle));
    }
}
