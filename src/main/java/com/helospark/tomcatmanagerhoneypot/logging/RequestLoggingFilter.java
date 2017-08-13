package com.helospark.tomcatmanagerhoneypot.logging;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Component
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {
    private Logger logger;

    public RequestLoggingFilter(Logger logger) {
        this.logger = logger;
        super.setIncludeQueryString(true);
        super.setIncludeClientInfo(true);
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return true;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info("Starting to process request. " + message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info("Finished processing request. " + message);
    }
}
