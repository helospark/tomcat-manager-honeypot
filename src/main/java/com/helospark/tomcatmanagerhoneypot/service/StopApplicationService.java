package com.helospark.tomcatmanagerhoneypot.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class StopApplicationService {
    private String stopTemplate;

    public StopApplicationService(@Value("${honeypot.stop.response}") String reloadTemplate) {
        this.stopTemplate = reloadTemplate;
    }

    public String stopApplication(@NotNull @RequestParam("path") String path) {
        return String.format(stopTemplate, path);
    }
}
