package com.helospark.tomcatmanagerhoneypot.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.helospark.tomcatmanagerhoneypot.service.helper.ResourceToStringReader;

@Service
public class ListDeployedApplicationService {
    private String runningApplications;
    private Resource resource;
    private ResourceToStringReader resourceToStringReader;

    public ListDeployedApplicationService(@Value("${honeypot.running.applications.file}") Resource resource,
            ResourceToStringReader resourceToStringReader) {
        this.resource = resource;
        this.resourceToStringReader = resourceToStringReader;
    }

    @PostConstruct
    public void initialize() {
        runningApplications = resourceToStringReader.read(resource);
    }

    public String handle() {
        return runningApplications;
    }

}
