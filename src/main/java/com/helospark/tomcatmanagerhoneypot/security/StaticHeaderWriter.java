package com.helospark.tomcatmanagerhoneypot.security;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.util.ReflectionUtils;

public class StaticHeaderWriter implements HeaderWriter {
    private final Method getHeaderMethod;
    private final HeaderWriter delegate;
    private List<Header> headers;

    public StaticHeaderWriter(List<Header> headers) {
        this.delegate = new StaticHeadersWriter(headers);
        this.headers = headers;
        this.getHeaderMethod = ReflectionUtils.findMethod(HttpServletResponse.class,
                "getHeader", String.class);
    }

    @Override
    public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
        if (hasAnyHeader(response, headers)) {
            return;
        }
        this.delegate.writeHeaders(request, response);
    }

    private boolean hasAnyHeader(HttpServletResponse response, List<Header> headers) {
        return headers.stream()
                .filter(header -> hasHeader(response, header))
                .findAny()
                .isPresent();
    }

    private boolean hasHeader(HttpServletResponse response, Header header) {
        if (this.getHeaderMethod == null) {
            return false;
        }
        return ReflectionUtils.invokeMethod(this.getHeaderMethod, response,
                header.getName()) != null;
    }

}
