package com.helospark.tomcatmanagerhoneypot.text;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.service.ListDeployedApplicationService;

@RestController
public class ListDeployedApplicationController {
    private ListDeployedApplicationService listDeployedApplicationService;

    public ListDeployedApplicationController(ListDeployedApplicationService listDeployedApplicationService) {
        this.listDeployedApplicationService = listDeployedApplicationService;
    }

    @GetMapping(path = "/text/list")
    public String handle() {
        return listDeployedApplicationService.handle();
    }
}
