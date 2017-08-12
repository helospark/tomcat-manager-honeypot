package com.helospark.tomcatmanagerhoneypot.service.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ResourceToStringReader {

    public String read(Resource resource) {
        try {
            return new BufferedReader(new InputStreamReader(resource.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read file " + resource.getFilename(), e);
        }
    }
}
