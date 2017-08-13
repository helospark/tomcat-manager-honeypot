package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;
import com.helospark.tomcatmanagerhoneypot.service.ServerInformationService;

@Controller
@HtmlExceptionHandler
public class HtmlServerInformationController {
    private ServerInformationService serverInformationService;

    public HtmlServerInformationController(ServerInformationService serverInformationService) {
        this.serverInformationService = serverInformationService;
    }

    @ModelAttribute("status")
    public String getStatus() {
        return serverInformationService.getServerInformation();
    }

    @RequestMapping("/html/serverinfo")
    public String handle() {
        return "manager";
    }
}
