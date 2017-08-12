package com.helospark.tomcatmanagerhoneypot.it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.helospark.tomcatmanagerhoneypot.service.helper.CurrentTimestampProvider;

@TestPropertySource(properties = "honeypot.save.directory=/tmp")
public class TextDeployControllerTest extends AbstractTextControllerIT {
    private static final String TEST_FILE_CONTENT = "Test";
    private static final String EXPECTED_FILE_NAME = "/tmp/_textdeploycontrollertestfile_123456789";
    @MockBean
    private CurrentTimestampProvider currentTimestamp;

    @Before
    public void setUp() {
        when(currentTimestamp.getCurrentTimeStampAsString()).thenReturn("123456789");
        clearTmpFileIfExists();
    }

    @After
    public void tearDown() {
        clearTmpFileIfExists();
    }

    private void clearTmpFileIfExists() {
        File file = new File(EXPECTED_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testDeployHappyFlowShouldCreateWarFile() {
        // GIVEN

        // WHEN
        ResponseEntity<String> result = sendAuthenticatedRequest("textdeploycontrollertestfile",
                TEST_FILE_CONTENT.getBytes());

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is("OK - Deployed application at context path /textdeploycontrollertestfile"));
        assertThatFileContainsExpectedContent();
    }

    private void assertThatFileContainsExpectedContent() {
        File file = new File(EXPECTED_FILE_NAME);
        assertThat(file.exists(), is(true));
        assertThat(readFileContent(file), is(TEST_FILE_CONTENT));
    }

    private String readFileContent(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] data = new byte[10];
            int readBytes = fileInputStream.read(data);
            return new String(data, 0, readBytes, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeployWithVeryLongPathName() {
        // GIVEN

        // WHEN
        String tooLongPathParam = String.join("", Collections.nCopies(100, "test"));
        ResponseEntity<String> result = sendAuthenticatedRequest(tooLongPathParam, TEST_FILE_CONTENT.getBytes());

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void testDeployWithTooLongBody() {
        // GIVEN
        int fiftyMegabytes = 50 * 1024 * 1024;
        byte[] tooLargeBody = new byte[fiftyMegabytes];

        // WHEN
        ResponseEntity<String> result = sendAuthenticatedRequest("textdeploycontrollertestfile", tooLargeBody);

        // THEN
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    private ResponseEntity<String> sendAuthenticatedRequest(String pathName, byte[] body) {
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body);
        return testRestTemplate.exchange(
                super.composeUrl("deploy?path=/" + pathName), HttpMethod.PUT,
                httpEntity, String.class);
    }

}
