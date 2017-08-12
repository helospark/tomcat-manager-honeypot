package com.helospark.tomcatmanagerhoneypot.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.helospark.tomcatmanagerhoneypot.service.helper.ResourceToStringReader;

@Service
public class ExpireSessionService {
    private String expireSession;
    private Resource resource;
    private ResourceToStringReader resourceToStringReader;

    public ExpireSessionService(@Value("${honeypot.expiresession.file}") Resource resource,
            ResourceToStringReader resourceToStringReader) {
        this.resource = resource;
        this.resourceToStringReader = resourceToStringReader;
    }

    @PostConstruct
    public void initialize() {
        expireSession = resourceToStringReader.read(resource);
    }

    public String expireSessions(int time, String path) {
        return String.format(expireSession, path, time, time);
    }

}
