package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TopMenuIT extends AbstractUiIT {
    @Before
    public void setUp() {
        super.init();
        performCall("/manager/html");
    }

    @After
    public void tearDown() {
        super.destroy();
    }

    @Test
    public void testAfterLoadingMainPageShouldLoadExpectedPage() {
        // GIVEN

        // WHEN page is loaded

        // THEN
        assertPageTitleIs("Tomcat Web Application Manager");
    }

    @Test
    public void testClickOnServerStatusShouldLoadPage() {
        // GIVEN

        // WHEN
        webDriver.findElement(By.linkText("Server Status")).click();

        // THEN
        assertPageTitleIs("Server Status");
    }

    @Test
    public void testClickOnCompleteServerStatusShouldLoadSamePage() {
        // GIVEN
        webDriver.findElement(By.linkText("Server Status")).click();

        // WHEN
        webDriver.findElement(By.linkText("Complete Server Status")).click();

        // THEN
        assertPageTitleIs("Server Status");
    }

    @Test
    public void testServerStatusShouldContainExpectedTable() {
        // GIVEN

        // WHEN
        webDriver.findElement(By.linkText("Server Status")).click();

        // THEN
        String tableAsString = webDriver.findElement(By.xpath("/html/body/table[4]")).getText();
        assertThat(tableAsString, is(
                "Server Information Tomcat Version JVM Version JVM Vendor OS Name OS Version OS Architecture Hostname IP Address Apache Tomcat/9.0.0.M26 1.8.0_121-b13 Oracle Corporation Linux 4.8.0-56-generic amd64 server 127.0.1.1"));
    }

    @Test
    public void testManageHelpShouldGoToExpectedUrl() {
        // GIVEN

        // WHEN
        webDriver.findElement(By.linkText("Manager Help")).click();

        // THEN
        String url = webDriver.getCurrentUrl();
        assertTrue(url.matches("http://tomcat:tomcat@localhost:\\d+/docs/manager-howto\\.html.*"));
    }

    @Test
    public void testHtmlManageHelpShouldGoToExpectedUrl() {
        // GIVEN

        // WHEN
        webDriver.findElement(By.linkText("HTML Manager Help")).click();

        // THEN
        String url = webDriver.getCurrentUrl();
        assertTrue(url.matches("http://tomcat:tomcat@localhost:\\d+/docs/html-manager-howto\\.html.*"));
    }

    @Test
    public void testListApplicationShouldGoToExpectedUrl() {
        // GIVEN

        // WHEN
        webDriver.findElement(By.linkText("List Applications")).click();

        // THEN
        String url = webDriver.getCurrentUrl();
        assertTrue(url.matches("http://tomcat:tomcat@localhost:\\d+/manager/html/list.*"));
    }
}
