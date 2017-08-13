package com.helospark.tomcatmanagerhoneypot.text;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.RestErrorHandler;
import com.helospark.tomcatmanagerhoneypot.service.SessionInformationService;

@RestController
@RestErrorHandler
public class SessionInformationController {
    private SessionInformationService sessionInformationService;

    public SessionInformationController(SessionInformationService sessionInformationService) {
        this.sessionInformationService = sessionInformationService;
    }

    @GetMapping(path = "/text/session")
    public String handle(@RequestParam("path") @NotNull String path) {
        return sessionInformationService.getSessionInformation(path);
    }
}
