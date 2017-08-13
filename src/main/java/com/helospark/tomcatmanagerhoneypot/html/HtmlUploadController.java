package com.helospark.tomcatmanagerhoneypot.html;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;
import com.helospark.tomcatmanagerhoneypot.service.UploadApplicationService;

@Controller
@HtmlExceptionHandler
public class HtmlUploadController {
    private UploadApplicationService deployApplicationService;
    private MultipartFileFilenameExtractor multipartFileFilenameExtractor;

    public HtmlUploadController(UploadApplicationService deployApplicationService,
            MultipartFileFilenameExtractor multipartFileFilenameExtractor, Logger logger) {
        this.deployApplicationService = deployApplicationService;
        this.multipartFileFilenameExtractor = multipartFileFilenameExtractor;
    }

    @ModelAttribute("status")
    public String getStatus(@RequestParam("deployWar") MultipartFile file) throws IOException {
        String filename = multipartFileFilenameExtractor.extractFilename(file);
        return deployApplicationService.uploadApplication(filename, file.getBytes());
    }

    @RequestMapping("/html/upload")
    public String handle() throws IOException {
        return "manager";
    }

}
