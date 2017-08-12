package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helospark.tomcatmanagerhoneypot.service.SessionInformationService;

@Controller
public class HtmlSessionInformationController {
    private SessionInformationService sessionInformationService;

    public HtmlSessionInformationController(SessionInformationService sessionInformationService) {
        this.sessionInformationService = sessionInformationService;
    }

    @ModelAttribute("status")
    public String getStatus(@RequestParam("path") String path) {
        return sessionInformationService.getSessionInformation(path);
    }

    @RequestMapping("/html/sessions")
    public String handle() {
        return "manager";
    }
}
