package com.helospark.tomcatmanagerhoneypot.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.header.Header;

@Configuration
public class HeaderConfiguration {

    @Bean("privateCacheControlHeaderWriter")
    public StaticHeaderWriter privateCacheControlHeaderWriter() {
        return new StaticHeaderWriter(createPrivateCacheControlHeaders());
    }

    @Bean("serverHeaderWriter")
    public StaticHeaderWriter serverHeaderWriter() {
        return new StaticHeaderWriter(createServerHeader());
    }

    private List<Header> createPrivateCacheControlHeaders() {
        List<Header> headers = new ArrayList<Header>(1);
        headers.add(new Header("Cache-Control", "private"));
        headers.add(new Header("Expires", "Thu, 01 Jan 1970 01:00:00 CET"));
        return headers;
    }

    private List<Header> createServerHeader() {
        return Collections.singletonList(new Header("Server", "Apache-Coyote/1.1"));
    }
}
