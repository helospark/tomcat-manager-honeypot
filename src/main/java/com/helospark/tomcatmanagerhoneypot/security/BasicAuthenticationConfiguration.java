package com.helospark.tomcatmanagerhoneypot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BasicAuthenticationConfiguration extends WebSecurityConfigurerAdapter {
    private LoggingBasicAuthenticationEntryPoint loggingBasicAuthenticationEntryPoint;
    private String userName;
    private String password;

    public BasicAuthenticationConfiguration(LoggingBasicAuthenticationEntryPoint loggingBasicAuthenticationEntryPoint,
            @Value("${honeypot.basicauthentication.username}") String userName,
            @Value("${honeypot.basicauthentication.password}") String password) {
        this.loggingBasicAuthenticationEntryPoint = loggingBasicAuthenticationEntryPoint;
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
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(userName)
                .password(password)
                .roles("USER");
    }

}
