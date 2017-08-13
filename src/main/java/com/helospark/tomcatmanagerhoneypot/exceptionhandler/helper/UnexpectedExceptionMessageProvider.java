package com.helospark.tomcatmanagerhoneypot.exceptionhandler.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UnexpectedExceptionMessageProvider {
    private String unexpectedExceptionMessage;

    public UnexpectedExceptionMessageProvider(
            @Value("${honeypot.error.unexpected.response}") String unexpectedExceptionMessage) {
        this.unexpectedExceptionMessage = unexpectedExceptionMessage;
    }

    public String getMessage() {
        return unexpectedExceptionMessage;
    }

}
