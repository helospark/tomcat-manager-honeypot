package com.helospark.tomcatmanagerhoneypot.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UndeployApplicationService {
    private String undeployTemplate;

    public UndeployApplicationService(@Value("${honeypot.undeploy.response}") String reloadTemplate) {
        this.undeployTemplate = reloadTemplate;
    }

    @GetMapping("/text/undeploy")
    public String undeployApplication(@NotNull @RequestParam("path") String path) {
        return String.format(undeployTemplate, path);
    }

}
