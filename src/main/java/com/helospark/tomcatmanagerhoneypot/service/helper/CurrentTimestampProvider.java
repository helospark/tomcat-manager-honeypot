package com.helospark.tomcatmanagerhoneypot.service.helper;

import org.springframework.stereotype.Component;

@Component
public class CurrentTimestampProvider {

    public String getCurrentTimeStampAsString() {
        return String.valueOf(System.currentTimeMillis());
    }
}
