package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;
import com.helospark.tomcatmanagerhoneypot.service.ReloadApplicationService;

@Controller
@HtmlExceptionHandler
public class HtmlReloadApplicationController {
    private ReloadApplicationService reloadApplicationService;

    public HtmlReloadApplicationController(ReloadApplicationService reloadApplicationService) {
        this.reloadApplicationService = reloadApplicationService;
    }

    @ModelAttribute("status")
    public String getStatus(@RequestParam("path") String path) {
        return reloadApplicationService.reloadApplication(path);
    }

    @RequestMapping("/html/reload")
    public String handle() {
        return "manager";
    }
}
