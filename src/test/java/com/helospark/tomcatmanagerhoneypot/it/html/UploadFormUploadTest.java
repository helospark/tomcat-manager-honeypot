package com.helospark.tomcatmanagerhoneypot.it.html;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.helospark.tomcatmanagerhoneypot.service.helper.CurrentTimestampProvider;

public class UploadFormUploadTest extends AbstractUiIT {
    private WebElement deployForm;
    private String expectedFileName = "/tmp/tomcat_manager_honeypot_upload_test_123456789";

    @MockBean
    private CurrentTimestampProvider currentTimestamp;

    @Before
    public void setUp() {
        super.init();
        performCall("/manager/html");
        when(currentTimestamp.getCurrentTimeStampAsString()).thenReturn("123456789");
        deployForm = findFormWithAction("/manager/html/upload");
        clearFileIfExists(expectedFileName);
    }

    @After
    public void tearDown() {
        super.destroy();
        clearFileIfExists(expectedFileName);
    }

    @Test
    public void testSubmitEmptyFormShouldResultErrorMessage() {
        // GIVEN

        // WHEN
        deployForm.submit();

        // THEN
        WebElement error = getStatusMessage();
        assertThat(error.getText(), is("FAIL - Invalid deployWar [] was specified"));
    }

    @Test
    public void testSubmitFilledFormShouldResultInOkMessage() throws IOException {
        String filePath = "/tmp/tomcat_manager_honeypot_upload_test";
        byte[] fileData = "test".getBytes();
        File fileToUpload = createFileWithMessage(filePath, fileData);
        browseFileInForm(filePath);

        // WHEN
        deployForm.submit();

        // THEN
        assertOkStatus();
        assertThatFileExistsWithContent(expectedFileName, fileData);

        // Cleanup
        fileToUpload.delete();
    }

    private void assertThatFileExistsWithContent(String fileName, byte[] fileData)
            throws IOException, FileNotFoundException {
        File resultFile = new File(expectedFileName);
        assertTrue(resultFile.exists());
        try (FileInputStream fileInputStream = new FileInputStream(resultFile)) {
            byte[] actualData = new byte[fileData.length];
            fileInputStream.read(actualData);
            assertThat(actualData, is(fileData));
        }
    }

    private void assertOkStatus() {
        WebElement status = getStatusMessage();
        assertThat(status.getText(),
                is("OK - Deployed application at context path tomcat_manager_honeypot_upload_test"));
    }

    private void browseFileInForm(String filePath) {
        deployForm.findElement(By.name("deployWar")).sendKeys(filePath);
    }

    private File createFileWithMessage(String filePath, byte[] fileData) throws IOException, FileNotFoundException {
        File file = new File(filePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(fileData);
        }
        return file;
    }

    private void clearFileIfExists(String expectedFileName2) {
        File file = new File(expectedFileName);
        if (file.exists()) {
            file.delete();
        }
    }

}
