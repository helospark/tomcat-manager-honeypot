package com.helospark.tomcatmanagerhoneypot.service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.helospark.tomcatmanagerhoneypot.service.helper.ResourceToStringReader;

@Service
public class SessionInformationService {
    private String sessionInformationTemplate;
    private Resource resource;
    private ResourceToStringReader resourceToStringReader;

    public SessionInformationService(@Value("${honeypot.sessioninformation.file}") Resource resource,
            ResourceToStringReader resourceToStringReader) {
        this.resource = resource;
        this.resourceToStringReader = resourceToStringReader;
    }

    @PostConstruct
    public void initialize() {
        sessionInformationTemplate = resourceToStringReader.read(resource);
    }

    public String getSessionInformation(@RequestParam("path") @NotNull String path) {
        return String.format(sessionInformationTemplate, path);
    }
}
