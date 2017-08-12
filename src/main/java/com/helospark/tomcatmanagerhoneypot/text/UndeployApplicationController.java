package com.helospark.tomcatmanagerhoneypot.text;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.service.UndeployApplicationService;

@RestController
public class UndeployApplicationController {
    private UndeployApplicationService undeployApplicationService;

    public UndeployApplicationController(UndeployApplicationService undeployApplicationService) {
        this.undeployApplicationService = undeployApplicationService;
    }

    @GetMapping("/text/undeploy")
    public String handle(@NotNull @RequestParam("path") String path) {
        return undeployApplicationService.undeployApplication(path);
    }
}
