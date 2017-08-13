package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;
import com.helospark.tomcatmanagerhoneypot.service.StartApplicationService;

@Controller
@HtmlExceptionHandler
public class HtmlStartApplicationController {
    private StartApplicationService startApplicationService;

    public HtmlStartApplicationController(StartApplicationService startApplicationService) {
        this.startApplicationService = startApplicationService;
    }

    @ModelAttribute("status")
    public String getStatus(@RequestParam("path") String path) {
        return startApplicationService.startApplication(path);
    }

    @RequestMapping("/html/start")
    public String handle() {
        return "manager";
    }
}
