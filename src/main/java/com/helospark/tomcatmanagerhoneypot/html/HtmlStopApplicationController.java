package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helospark.tomcatmanagerhoneypot.service.StopApplicationService;

@Controller
public class HtmlStopApplicationController {
    private StopApplicationService stopApplicationService;

    public HtmlStopApplicationController(StopApplicationService stopApplicationService) {
        this.stopApplicationService = stopApplicationService;
    }

    @ModelAttribute("status")
    public String getStatus(@RequestParam("path") String path) {
        return stopApplicationService.stopApplication(path);
    }

    @RequestMapping("/html/stop")
    public String handle() {
        return "manager";
    }
}
