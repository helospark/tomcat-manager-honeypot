package com.helospark.tomcatmanagerhoneypot.logging;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RequestIpExtractor {
    public static final String UNKNOWN_REQUEST_ID = "Unknown";
    private String realIpHeader;

    public RequestIpExtractor(@Value("${honeypot.clientip.headername:}") String realIpHeader) {
        this.realIpHeader = realIpHeader;
    }

    public String getRequestIp(ServletRequest servletRequest) {
        if (!realIpHeader.isEmpty() && servletRequest instanceof HttpServletRequest) {
            return extractFromHeader((HttpServletRequest) servletRequest);
        } else {
            return extractIp(servletRequest);
        }
    }

    private String extractUserIdentifier(ServletRequest servletRequest) {
        if (!realIpHeader.isEmpty() && servletRequest instanceof HttpServletRequest) {
            return extractFromHeader((HttpServletRequest) servletRequest);
        } else {
            return extractIp(servletRequest);
        }
    }

    private String extractFromHeader(HttpServletRequest servletRequest) {
        String header = servletRequest.getHeader(realIpHeader);
        if (header == null || header.isEmpty()) {
            return UNKNOWN_REQUEST_ID;
        } else {
            return header;
        }
    }

    private String extractIp(ServletRequest servletRequest) {
        return servletRequest.getRemoteAddr();
    }
}
