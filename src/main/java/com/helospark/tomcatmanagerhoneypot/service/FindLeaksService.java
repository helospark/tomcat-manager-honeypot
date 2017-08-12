package com.helospark.tomcatmanagerhoneypot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FindLeaksService {
    private String findLeaksResponse;

    public FindLeaksService(@Value("${honeypot.findleaks.response}") String findLeaksResponse) {
        this.findLeaksResponse = findLeaksResponse;
    }

    public String findLeaks() {
        return findLeaksResponse;
    }
}
