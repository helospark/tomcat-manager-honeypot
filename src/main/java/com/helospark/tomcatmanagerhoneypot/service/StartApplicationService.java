package com.helospark.tomcatmanagerhoneypot.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class StartApplicationService {
    private String startTemplate;

    public StartApplicationService(@Value("${honeypot.start.response}") String reloadTemplate) {
        this.startTemplate = reloadTemplate;
    }

    public String startApplication(@NotNull @RequestParam("path") String path) {
        return String.format(startTemplate, path);
    }
}
