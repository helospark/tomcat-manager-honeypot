package com.helospark.tomcatmanagerhoneypot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class BasicAuthenticationConfiguration extends WebSecurityConfigurerAdapter {
    private LoggingBasicAuthenticationEntryPoint loggingBasicAuthenticationEntryPoint;
    private StaticHeaderWriter privateCacheControlHeaderWriter;
    private StaticHeaderWriter serverHeaderWriter;
    private String userName;
    private String password;

    public BasicAuthenticationConfiguration(LoggingBasicAuthenticationEntryPoint loggingBasicAuthenticationEntryPoint,
            @Qualifier("privateCacheControlHeaderWriter") StaticHeaderWriter privateCacheControlHeaderWriter,
            @Qualifier("serverHeaderWriter") StaticHeaderWriter serverHeaderWriter,
            @Value("${honeypot.basicauthentication.username}") String userName,
            @Value("${honeypot.basicauthentication.password}") String password) {
        this.loggingBasicAuthenticationEntryPoint = loggingBasicAuthenticationEntryPoint;
        this.privateCacheControlHeaderWriter = privateCacheControlHeaderWriter;
        this.serverHeaderWriter = serverHeaderWriter;
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/image/*").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic().authenticationEntryPoint(loggingBasicAuthenticationEntryPoint)
                .and()
                .csrf().disable();

        // Turning following security options would not be secure in an actual application
        // but in this honeypot, we are mimicking the Tomcat manager's return headers
        http.headers()
                .cacheControl().disable()
                .and()
                .headers().addHeaderWriter(privateCacheControlHeaderWriter)
                .and()
                .headers().xssProtection().disable()
                .and()
                .headers().frameOptions().disable()
                .and()
                .headers().defaultsDisabled().contentTypeOptions().disable()
                .and()
                .headers().addHeaderWriter(serverHeaderWriter);

        // set session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(userName)
                .password(password)
                .roles("USER");
    }

}
