package com.helospark.tomcatmanagerhoneypot.exceptionhandler.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

@Component
public class BindExceptionMessageExtractor {
    private static final int RESPONSE_REJECTED_VALUE_MAX_LENGTH = 100;
    private String unexpectedExceptionMessage;
    private String bindExceptionTemplate;

    public BindExceptionMessageExtractor(
            @Value("${honeypot.error.unexpected.response}") String unexpectedExceptionMessage,
            @Value("${honeypot.error.bindexception.response}") String bindExceptionTemplate) {
        this.unexpectedExceptionMessage = unexpectedExceptionMessage;
        this.bindExceptionTemplate = bindExceptionTemplate;
    }

    public String getErrorMessage(BindException ex) {
        return getErrorMessage(ex, true);
    }

    public String getErrorMessage(BindException ex, boolean includeValue) {
        Optional<FieldError> firstError = ex.getAllErrors()
                .stream()
                .filter(error -> error instanceof FieldError)
                .map(error -> (FieldError) error)
                .findFirst();
        if (firstError.isPresent()) {
            String field = firstError.get().getField(); // We could use annotation to make more believable error message
            String rejectedValue = extractRejectedValue(firstError, includeValue);
            return String.format(bindExceptionTemplate, field, rejectedValue);
        }
        return unexpectedExceptionMessage;
    }

    private String extractRejectedValue(Optional<FieldError> firstError, boolean includeRejectedValue) {
        if (includeRejectedValue) {
            String rejectedValue = String.valueOf(firstError.get().getRejectedValue());
            if (rejectedValue != null && rejectedValue.length() > RESPONSE_REJECTED_VALUE_MAX_LENGTH) {
                rejectedValue = rejectedValue.substring(0, RESPONSE_REJECTED_VALUE_MAX_LENGTH);
            }
            return rejectedValue;
        } else {
            return "";
        }
    }
}
