package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;
import com.helospark.tomcatmanagerhoneypot.service.UndeployApplicationService;

@Controller
@HtmlExceptionHandler
public class HtmlUndeployApplicationController {
    private UndeployApplicationService undeployApplicationService;

    public HtmlUndeployApplicationController(UndeployApplicationService undeployApplicationService) {
        this.undeployApplicationService = undeployApplicationService;
    }

    @ModelAttribute("status")
    public String getStatus(@RequestParam("path") String path) {
        return undeployApplicationService.undeployApplication(path);
    }

    @RequestMapping("/html/undeploy")
    public String handle() {
        return "manager";
    }
}
