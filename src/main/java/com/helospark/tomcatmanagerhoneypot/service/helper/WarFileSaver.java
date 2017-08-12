package com.helospark.tomcatmanagerhoneypot.service.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WarFileSaver {
    private String rootPath;
    private File rootDirectory;
    private UniqueFileFinder uniqueFileFinder;

    public WarFileSaver(@Value("${honeypot.save.directory}") String rootPath, UniqueFileFinder uniqueFileFinder) {
        this.uniqueFileFinder = uniqueFileFinder;
        this.rootPath = rootPath;
    }

    @PostConstruct
    public void initializeDirectory() {
        rootDirectory = new File(rootPath);
        if (rootDirectory.exists() && !rootDirectory.isDirectory()) {
            throw new IllegalArgumentException(rootDirectory.getAbsolutePath() + " exists, but it is not a directory");
        }
        if (!rootDirectory.exists()) {
            boolean createdDirectory = rootDirectory.mkdirs();
            if (!createdDirectory) {
                throw new IllegalStateException("Cannot create root directory " + rootDirectory.getAbsolutePath()
                        + " make sure this application has permission to do so.");
            }
        }
    }

    public void saveWarFile(String baseName, byte[] data) {
        File file = uniqueFileFinder.generateUniqueFile(rootDirectory, baseName);
        try {
            file.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(data);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to create file, check permissions: " + file.getAbsolutePath(), e);
        }
    }
}
