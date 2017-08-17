package com.helospark.tomcatmanagerhoneypot.html.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;

public class NotEmptyMultipartFileValidatorTest {
    private NotEmptyMultipartFileValidator underTest;
    @Mock
    private ConstraintValidatorContext constraintValidatorContext;
    @Mock
    private MultipartFile multipartFile;

    @Before
    public void setUp() {
        initMocks(this);
        underTest = new NotEmptyMultipartFileValidator();
    }

    @Test
    public void testIsValidShouldReturnFalseWhenMultipartFileIsEmpty() {
        // GIVEN
        when(multipartFile.getOriginalFilename()).thenReturn("");

        // WHEN
        boolean result = underTest.isValid(multipartFile, constraintValidatorContext);

        // THEN
        assertFalse(result);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenFilenameIsNull() {
        // GIVEN
        when(multipartFile.getOriginalFilename()).thenReturn(null);

        // WHEN
        boolean result = underTest.isValid(multipartFile, constraintValidatorContext);

        // THEN
        assertFalse(result);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenByteArrayIsEmpty() throws IOException {
        // GIVEN
        when(multipartFile.getOriginalFilename()).thenReturn("valid");
        when(multipartFile.getBytes()).thenReturn(new byte[0]);

        // WHEN
        boolean result = underTest.isValid(multipartFile, constraintValidatorContext);

        // THEN
        assertFalse(result);
    }

    @Test
    public void testIsValidShouldReturnTrueWhenByteArrayIsFilled() throws IOException {
        // GIVEN
        when(multipartFile.getOriginalFilename()).thenReturn("valid");
        when(multipartFile.getBytes()).thenReturn(new byte[2]);

        // WHEN
        boolean result = underTest.isValid(multipartFile, constraintValidatorContext);

        // THEN
        assertTrue(result);
    }
}
