package com.helospark.tomcatmanagerhoneypot.logging;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MdcInitializingFilter extends OncePerRequestFilter {
    private static final String REQUEST_ID = "RequestId";
    private RequestIdFactory userIdentifierExtractor;

    public MdcInitializingFilter(RequestIdFactory userIdentifierExtractor) {
        this.userIdentifierExtractor = userIdentifierExtractor;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        requestInitialized(request);
        filterChain.doFilter(request, response);
        requestDestroyed(request);
    }

    public void requestInitialized(HttpServletRequest request) {
        String requestId = userIdentifierExtractor.createRequestId(request);
        MDC.put(REQUEST_ID, requestId);
    }

    public void requestDestroyed(HttpServletRequest request) {
        MDC.remove(REQUEST_ID);
    }
}
