package com.helospark.tomcatmanagerhoneypot.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class LoggingBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    private Logger logger;

    public LoggingBasicAuthenticationEntryPoint(Logger logger,
            @Value("${honeypot.basicauthentication.realmname}") String realmName) {
        this.logger = logger;
        super.setRealmName(realmName);
    }

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        logAuthenticationFailure(request, authException);
        super.commence(request, response, authException);
    }

    private void logAuthenticationFailure(HttpServletRequest request, AuthenticationException authException)
            throws UnsupportedEncodingException {
        try {
            if (authException != null) {
                if (authException instanceof BadCredentialsException) {
                    String authorizationHeader = request.getHeader("Authorization");
                    if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
                        byte[] base64Token = authorizationHeader.substring(6).getBytes("UTF-8");
                        String decodedHeader = new String(Base64.decode(base64Token), "UTF-8");
                        logger.info("Bad credentials: '" + decodedHeader + "'");
                    }
                } else {
                    logger.info(authException.getMessage());
                }
            }
        } catch (Exception e) {
            logger.error("Unexpected exception while logging", e);
        }
    }

}
