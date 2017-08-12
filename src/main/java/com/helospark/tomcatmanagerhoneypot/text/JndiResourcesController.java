package com.helospark.tomcatmanagerhoneypot.text;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.service.JndiResourcesService;

@RestController
public class JndiResourcesController {
    private JndiResourcesService jndiResourcesService;

    public JndiResourcesController(JndiResourcesService jndiResourcesService) {
        this.jndiResourcesService = jndiResourcesService;
    }

    @GetMapping(path = "/text/resources")
    public String handle(@RequestParam("type") String type) {
        return jndiResourcesService.handle(type);
    }
}
