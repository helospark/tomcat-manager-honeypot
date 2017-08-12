package com.helospark.tomcatmanagerhoneypot.service.helper;

import org.springframework.stereotype.Component;

@Component
public class FileNameClearer {

    public String clearFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9]", "_");
    }
}
