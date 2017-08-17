package com.helospark.tomcatmanagerhoneypot.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;
import com.helospark.tomcatmanagerhoneypot.exceptionhandler.helper.BindExceptionMessageExtractor;
import com.helospark.tomcatmanagerhoneypot.exceptionhandler.helper.UnexpectedExceptionMessageProvider;

@ControllerAdvice(annotations = HtmlExceptionHandler.class)
public class HtmlExceptionHandlerControllerAdvice {
    private BindExceptionMessageExtractor bindExceptionMessageExtractor;
    private UnexpectedExceptionMessageProvider unexpectedExceptionMessageProvider;
    private Logger logger;

    public HtmlExceptionHandlerControllerAdvice(BindExceptionMessageExtractor bindExceptionMessageExtractor,
            UnexpectedExceptionMessageProvider unexpectedExceptionMessageProvider, Logger logger) {
        this.bindExceptionMessageExtractor = bindExceptionMessageExtractor;
        this.unexpectedExceptionMessageProvider = unexpectedExceptionMessageProvider;
        this.logger = logger;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processValidationError(HttpServletRequest request, BindException ex) {
        logger.warn("Bind exception: '{}'", ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager");
        modelAndView.addObject("status", bindExceptionMessageExtractor.getErrorMessage(ex));
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processUnexpectedException(Exception ex) {
        logger.error("Unexpected exception", ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager");
        modelAndView.addObject("status", unexpectedExceptionMessageProvider.getMessage());
        return modelAndView;
    }

}
