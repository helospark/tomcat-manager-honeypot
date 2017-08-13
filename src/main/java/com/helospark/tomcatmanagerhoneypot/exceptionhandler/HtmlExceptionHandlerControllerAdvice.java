package com.helospark.tomcatmanagerhoneypot.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

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

    public HtmlExceptionHandlerControllerAdvice(BindExceptionMessageExtractor bindExceptionMessageExtractor,
            UnexpectedExceptionMessageProvider unexpectedExceptionMessageProvider) {
        this.bindExceptionMessageExtractor = bindExceptionMessageExtractor;
        this.unexpectedExceptionMessageProvider = unexpectedExceptionMessageProvider;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processValidationError(HttpServletRequest request, BindException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager");
        modelAndView.addObject("status", bindExceptionMessageExtractor.getErrorMessage(ex));
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processUnexpectedException() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager");
        modelAndView.addObject("status", unexpectedExceptionMessageProvider.getMessage());
        return modelAndView;
    }

}
