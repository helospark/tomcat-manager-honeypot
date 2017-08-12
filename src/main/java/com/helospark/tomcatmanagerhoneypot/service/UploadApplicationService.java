package com.helospark.tomcatmanagerhoneypot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.helospark.tomcatmanagerhoneypot.service.helper.WarFileSaver;

@Service
public class UploadApplicationService {
    private WarFileSaver warFileSaver;
    private String responseTemplate;

    public UploadApplicationService(WarFileSaver warFileSaver,
            @Value("${honeypot.deploy.response}") String responseTemplate) {
        this.warFileSaver = warFileSaver;
        this.responseTemplate = responseTemplate;
    }

    public String uploadApplication(String path, byte[] body) {
        warFileSaver.saveWarFile(path, body);
        return String.format(responseTemplate, path);
    }
}
