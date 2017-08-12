package com.helospark.tomcatmanagerhoneypot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DeployApplicationService {
    private String responseTemplate;

    public DeployApplicationService(@Value("${honeypot.deploy.response}") String responseTemplate) {
        this.responseTemplate = responseTemplate;
    }

    public String uploadApplication(String path) {
        return String.format(responseTemplate, path);
    }

}
