package com.helospark.tomcatmanagerhoneypot.text;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.RestErrorHandler;
import com.helospark.tomcatmanagerhoneypot.service.ReloadApplicationService;

@RestController
@RestErrorHandler
public class ReloadApplicationController {
    private ReloadApplicationService reloadApplicationService;

    public ReloadApplicationController(ReloadApplicationService reloadApplicationService) {
        this.reloadApplicationService = reloadApplicationService;
    }

    @GetMapping("/text/reload")
    public String handle(@NotNull @RequestParam("path") String path) {
        return reloadApplicationService.reloadApplication(path);
    }
}
