package com.helospark.tomcatmanagerhoneypot.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.helospark.tomcatmanagerhoneypot.service.helper.ResourceToStringReader;

@Service
public class ServerInformationService {
    private String serverInfo;
    private Resource resource;
    private ResourceToStringReader resourceToStringReader;

    public ServerInformationService(@Value("${honeypot.serverinfo.file}") Resource resource,
            ResourceToStringReader resourceToStringReader) {
        this.resource = resource;
        this.resourceToStringReader = resourceToStringReader;
    }

    @PostConstruct
    public void initialize() {
        serverInfo = resourceToStringReader.read(resource);
    }

    public String getServerInformation() {
        return serverInfo;
    }

}
