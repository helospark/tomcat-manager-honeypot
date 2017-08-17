package com.helospark.tomcatmanagerhoneypot.html.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MultipartFileFilenameExtractor {
    private static final String UNKNOWN_FILENAME = "Unknown";
    private int maxFilenameLength;

    public MultipartFileFilenameExtractor(@Value("${honeypot.war.filename.maxlength}") int maxFilenameLength) {
        this.maxFilenameLength = maxFilenameLength;
    }

    public String extractFilename(MultipartFile file) {
        String filename = Optional.ofNullable(file.getOriginalFilename())
                .orElse(UNKNOWN_FILENAME);
        if (filename.length() > maxFilenameLength) {
            filename = filename.substring(0, maxFilenameLength);
        }
        return filename;
    }
}
