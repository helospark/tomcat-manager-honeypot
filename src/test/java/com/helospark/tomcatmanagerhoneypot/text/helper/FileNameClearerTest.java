package com.helospark.tomcatmanagerhoneypot.text.helper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.helospark.tomcatmanagerhoneypot.service.helper.FileNameClearer;

public class FileNameClearerTest {
    private FileNameClearer fileNameClearer;

    @Before
    public void setUp() {
        fileNameClearer = new FileNameClearer();
    }

    @Test
    public void testClearFileNameShouldNotChangeAlphanumericText() {
        // GIVEN

        // WHEN
        String result = fileNameClearer.clearFileName("alphanumeric1");

        // THEN
        assertThat(result, is("alphanumeric1"));
    }

    @Test
    public void testClearFileNameShouldReplaceNonAlphanumericCharacters() {
        // GIVEN

        // WHEN
        String result = fileNameClearer.clearFileName("/alphanumeric1");

        // THEN
        assertThat(result, is("_alphanumeric1"));
    }
}
