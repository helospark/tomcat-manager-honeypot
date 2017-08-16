package com.helospark.tomcatmanagerhoneypot.service.badips.helper;

import java.net.URI;
import java.util.Collections;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.helospark.tomcatmanagerhoneypot.service.badips.helper.domain.BadIpsResponse;

@Component
public class BadIpsRestCaller {
    @Value("${honeypot.badips.apikey}")
    private String apiKey;
    @Value("${honeypot.badips.uri}")
    private String reportUri;
    @Value("${honeypot.badips.setkey.uri}")
    private String setKeyUri;

    private RestTemplate restTemplate;
    private Logger logger;

    public BadIpsRestCaller(RestTemplate restTemplate, Logger logger) {
        this.restTemplate = restTemplate;
        this.logger = logger;
    }

    public void report(String ip) {
        logger.info("Reporting '{}' to BadIps", ip);
        setActiveKey();
        sendIp(ip);
    }

    private void sendIp(String ip) {
        try {
            URI uri = UriComponentsBuilder.fromUriString(reportUri)
                    .buildAndExpand(Collections.singletonMap("ip", ip))
                    .toUri();
            ResponseEntity<BadIpsResponse> responseEntity = restTemplate.getForEntity(uri, BadIpsResponse.class);
            logger.debug("BadIps returned '{}'", responseEntity);
        } catch (Exception e) {
            logger.error("Unable to send ip to BadIps", e);
        }
    }

    private void setActiveKey() {
        try {
            URI uri = UriComponentsBuilder.fromUriString(setKeyUri)
                    .buildAndExpand(Collections.singletonMap("key", apiKey))
                    .toUri();
            ResponseEntity<BadIpsResponse> responseEntity = restTemplate.getForEntity(uri, BadIpsResponse.class);
            logger.debug("BadIps returned '{}'", responseEntity);
        } catch (Exception e) {
            logger.error("Unable to set current key", e);
        }
    }
}
