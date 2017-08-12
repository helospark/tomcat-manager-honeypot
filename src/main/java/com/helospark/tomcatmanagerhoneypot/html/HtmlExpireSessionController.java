package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helospark.tomcatmanagerhoneypot.service.ExpireSessionService;

@Controller
public class HtmlExpireSessionController {
    private ExpireSessionService expireSessionService;

    public HtmlExpireSessionController(ExpireSessionService expireSessionService) {
        this.expireSessionService = expireSessionService;
    }

    @ModelAttribute("status")
    public String getStatus(@RequestParam("idle") int time, @RequestParam("path") String path) {
        return expireSessionService.expireSessions(time, path);
    }

    @RequestMapping("/html/expire")
    public String handle() {
        return "manager";
    }
}