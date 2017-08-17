package com.helospark.tomcatmanagerhoneypot.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.RestErrorHandler;
import com.helospark.tomcatmanagerhoneypot.exceptionhandler.helper.BindExceptionMessageExtractor;
import com.helospark.tomcatmanagerhoneypot.exceptionhandler.helper.UnexpectedExceptionMessageProvider;

@ControllerAdvice(annotations = RestErrorHandler.class)
public class RestHandlerControllerAdvice {
    private BindExceptionMessageExtractor bindExceptionMessageExtractor;
    private UnexpectedExceptionMessageProvider unexpectedExceptionMessageProvider;
    private Logger logger;

    public RestHandlerControllerAdvice(BindExceptionMessageExtractor bindExceptionMessageExtractor,
            UnexpectedExceptionMessageProvider unexpectedExceptionMessageProvider, Logger logger) {
        this.bindExceptionMessageExtractor = bindExceptionMessageExtractor;
        this.unexpectedExceptionMessageProvider = unexpectedExceptionMessageProvider;
        this.logger = logger;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String processValidationError(HttpServletRequest request, BindException ex) {
        logger.warn("Bind exception: '{}'", ex.getMessage());
        return bindExceptionMessageExtractor.getErrorMessage(ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String processUnexpectedException(Exception e) {
        logger.error("Unexpected exception", e);
        return unexpectedExceptionMessageProvider.getMessage();
    }

}
