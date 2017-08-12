package com.helospark.tomcatmanagerhoneypot.text;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helospark.tomcatmanagerhoneypot.service.FindLeaksService;

@RestController
public class FindLeaksController {
    private FindLeaksService findLeaksService;

    public FindLeaksController(FindLeaksService findLeaksService) {
        this.findLeaksService = findLeaksService;
    }

    @RequestMapping("/text/findleaks")
    public String handle() {
        return findLeaksService.findLeaks();
    }
}
