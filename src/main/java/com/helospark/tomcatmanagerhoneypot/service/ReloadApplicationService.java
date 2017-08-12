package com.helospark.tomcatmanagerhoneypot.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ReloadApplicationService {
    private String reloadTemplate;

    public ReloadApplicationService(@Value("${honeypot.reload.response}") String reloadTemplate) {
        this.reloadTemplate = reloadTemplate;
    }

    public String reloadApplication(@NotNull @RequestParam("path") String path) {
        return String.format(reloadTemplate, path);
    }
}
