package com.helospark.tomcatmanagerhoneypot.logging;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MdcInitializingFilter extends OncePerRequestFilter {
    private static final String USER_IP = "UserIp";
    private static final String REQUEST_ID = "RequestId";
    private RequestIpExtractor userIpExtractor;

    public MdcInitializingFilter(RequestIpExtractor userIdentifierExtractor) {
        this.userIpExtractor = userIdentifierExtractor;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        requestInitialized(request);
        filterChain.doFilter(request, response);
        requestDestroyed(request);
    }

    public void requestInitialized(HttpServletRequest request) {
        String requestId = userIpExtractor.getRequestIp(request);
        MDC.put(USER_IP, requestId);
        MDC.put(REQUEST_ID, UUID.randomUUID().toString());
    }

    public void requestDestroyed(HttpServletRequest request) {
        MDC.remove(USER_IP);
        MDC.remove(REQUEST_ID);
    }
}
