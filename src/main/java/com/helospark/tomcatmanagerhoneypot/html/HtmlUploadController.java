package com.helospark.tomcatmanagerhoneypot.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;
import com.helospark.tomcatmanagerhoneypot.exceptionhandler.helper.BindExceptionMessageExtractor;
import com.helospark.tomcatmanagerhoneypot.html.domain.UploadWarForm;
import com.helospark.tomcatmanagerhoneypot.html.helper.MultipartFileFilenameExtractor;
import com.helospark.tomcatmanagerhoneypot.service.UploadApplicationService;

@Controller
@HtmlExceptionHandler
public class HtmlUploadController {
    private UploadApplicationService deployApplicationService;
    private MultipartFileFilenameExtractor multipartFileFilenameExtractor;
    private Logger logger;
    private BindExceptionMessageExtractor bindExceptionMessageExtractor;

    public HtmlUploadController(UploadApplicationService deployApplicationService,
            MultipartFileFilenameExtractor multipartFileFilenameExtractor, Logger logger,
            BindExceptionMessageExtractor bindExceptionMessageExtractor) {
        this.deployApplicationService = deployApplicationService;
        this.multipartFileFilenameExtractor = multipartFileFilenameExtractor;
        this.logger = logger;
        this.bindExceptionMessageExtractor = bindExceptionMessageExtractor;
    }

    @ModelAttribute("status")
    public String getStatus(@Valid UploadWarForm uploadWarForm) throws IOException {
        MultipartFile file = uploadWarForm.getDeployWar();
        String filename = multipartFileFilenameExtractor.extractFilename(file);
        return deployApplicationService.uploadApplication(filename, file.getBytes());
    }

    @RequestMapping("/html/upload")
    public String handle() throws IOException {
        return "manager";
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processValidationError(HttpServletRequest request, BindException ex) {
        logger.warn("Bind exception: '{}'", ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager");
        modelAndView.addObject("status", bindExceptionMessageExtractor.getErrorMessage(ex, false));
        return modelAndView;
    }

}
