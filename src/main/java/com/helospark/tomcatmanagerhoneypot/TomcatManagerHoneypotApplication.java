package com.helospark.tomcatmanagerhoneypot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;

import com.helospark.tomcatmanagerhoneypot.logging.MdcInitializingFilter;
import com.helospark.tomcatmanagerhoneypot.logging.RequestLoggingFilter;

@SpringBootApplication
@PropertySources({
        @PropertySource("classpath:default_configuration.properties"),
        @PropertySource(value = "file:${CONFIG_LOCATION:/etc/tomcat-manager-honeypot/configuration.properties}", ignoreResourceNotFound = true)
})
public class TomcatManagerHoneypotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomcatManagerHoneypotApplication.class, args);
    }

    @Bean
    @Scope("prototype")
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
    }

    @Bean
    public FilterRegistrationBean mdcInitializingFilterRequestrationBean(MdcInitializingFilter mdcInitializingFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(mdcInitializingFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean loggingFilter(RequestLoggingFilter requestLoggingFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(requestLoggingFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(2);
        return registration;
    }
}
