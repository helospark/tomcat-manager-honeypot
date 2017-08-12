package com.helospark.tomcatmanagerhoneypot.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.helospark.tomcatmanagerhoneypot.service.helper.ResourceToStringReader;

@Service
public class JndiResourcesService {
    private String jndiResources;
    private Resource resource;
    private ResourceToStringReader resourceToStringReader;

    public JndiResourcesService(@Value("${honeypot.jndiresources.file}") Resource resource,
            ResourceToStringReader resourceToStringReader) {
        this.resource = resource;
        this.resourceToStringReader = resourceToStringReader;
    }

    @PostConstruct
    public void initialize() {
        jndiResources = resourceToStringReader.read(resource);
    }

    public String handle(@RequestParam("type") String type) {
        String actualType = Optional.ofNullable(type).orElse("all");
        return String.format(jndiResources, actualType);
    }

}
