package com.helospark.tomcatmanagerhoneypot.text;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.service.ServerInformationService;

@RestController
public class ServerInfoController {
    private ServerInformationService serverInfoService;

    public ServerInfoController(ServerInformationService serverInfoService) {
        this.serverInfoService = serverInfoService;
    }

    @GetMapping(path = "/text/serverinfo")
    public String handle() {
        return serverInfoService.getServerInformation();
    }
}
