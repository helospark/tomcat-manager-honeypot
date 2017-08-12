package com.helospark.tomcatmanagerhoneypot.text;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.service.StartApplicationService;

@RestController
public class StartApplicationController {
    private StartApplicationService startApplicationService;

    public StartApplicationController(StartApplicationService startApplicationService) {
        this.startApplicationService = startApplicationService;
    }

    @GetMapping("/text/start")
    public String handle(@NotNull @RequestParam("path") String path) {
        return startApplicationService.startApplication(path);
    }
}
