package com.helospark.tomcatmanagerhoneypot.html.validator;

import java.io.IOException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class NotEmptyMultipartFileValidator implements ConstraintValidator<NotEmptyMultipartFile, MultipartFile> {

    @Override
    public void initialize(NotEmptyMultipartFile annotation) {
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return validate(value);
    }

    private boolean validate(MultipartFile value) {
        try {
            if (value == null) {
                return true;
            }
            if (isFilenameEmpty(value) || isBodyEmpty(value)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isBodyEmpty(MultipartFile value) throws IOException {
        return value.getBytes().length == 0;
    }

    private boolean isFilenameEmpty(MultipartFile value) {
        String originalFileName = value.getOriginalFilename();
        return (originalFileName == null || originalFileName.isEmpty());
    }

}
