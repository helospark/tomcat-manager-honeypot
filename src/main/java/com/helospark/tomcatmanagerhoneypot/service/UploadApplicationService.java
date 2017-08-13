package com.helospark.tomcatmanagerhoneypot.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.helospark.tomcatmanagerhoneypot.service.helper.WarFileSaver;

@Service
public class UploadApplicationService {
    private WarFileSaver warFileSaver;
    private String responseTemplate;
    private Logger logger;

    public UploadApplicationService(WarFileSaver warFileSaver,
            @Value("${honeypot.deploy.response}") String responseTemplate, Logger logger) {
        this.warFileSaver = warFileSaver;
        this.responseTemplate = responseTemplate;
        this.logger = logger;
    }

    public String uploadApplication(String path, byte[] body) {
        warFileSaver.saveWarFile(path, body);
        logger.info("Successfully uploaded WAR file");
        return String.format(responseTemplate, path);
    }
}
