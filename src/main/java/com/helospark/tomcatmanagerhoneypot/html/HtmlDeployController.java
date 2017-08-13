package com.helospark.tomcatmanagerhoneypot.html;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;
import com.helospark.tomcatmanagerhoneypot.html.domain.DeployUrlParameters;
import com.helospark.tomcatmanagerhoneypot.service.DeployApplicationService;

@Controller
@HtmlExceptionHandler
public class HtmlDeployController {
    private DeployApplicationService deployApplicationService;

    public HtmlDeployController(DeployApplicationService deployApplicationService) {
        this.deployApplicationService = deployApplicationService;
    }

    @ModelAttribute("status")
    public String getStatus(@Valid DeployUrlParameters deployUrlParameters) {
        return deployApplicationService.uploadApplication(deployUrlParameters.getDeployWar());
    }

    @RequestMapping("/html/deploy")
    public String handle() {
        return "manager";
    }
}
