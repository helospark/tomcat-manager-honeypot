package com.helospark.tomcatmanagerhoneypot.text;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.service.StopApplicationService;

@RestController
public class StopApplicationController {
    private StopApplicationService stopApplicationService;

    public StopApplicationController(StopApplicationService stopApplicationService) {
        this.stopApplicationService = stopApplicationService;
    }

    @GetMapping("/text/stop")
    public String handle(@NotNull @RequestParam("path") String path) {
        return stopApplicationService.stopApplication(path);
    }
}
