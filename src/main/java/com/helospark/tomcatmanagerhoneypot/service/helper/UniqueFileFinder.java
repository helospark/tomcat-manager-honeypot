package com.helospark.tomcatmanagerhoneypot.service.helper;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class UniqueFileFinder {
    private CurrentTimestampProvider currentTimestampProvider;
    private FileNameClearer fileNameClearer;

    public UniqueFileFinder(CurrentTimestampProvider currentTimestampProvider, FileNameClearer fileNameClearer) {
        this.currentTimestampProvider = currentTimestampProvider;
        this.fileNameClearer = fileNameClearer;
    }

    public File generateUniqueFile(File rootDirectory, String baseFileName) {
        String cleanFileName = fileNameClearer.clearFileName(baseFileName);
        File file = new File(rootDirectory, composeName(cleanFileName));
        if (file.exists()) {
            throw new IllegalStateException("Same files are generated within same milliseconds with same name");
        }
        return file;
    }

    private String composeName(String baseFileName) {
        return baseFileName + "_" + currentTimestampProvider.getCurrentTimeStampAsString();
    }
}
