package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;
import com.helospark.tomcatmanagerhoneypot.html.domain.ExpireSessionUrlParameters;
import com.helospark.tomcatmanagerhoneypot.service.ExpireSessionService;

@Controller
@HtmlExceptionHandler
public class HtmlExpireSessionController {
    private ExpireSessionService expireSessionService;

    public HtmlExpireSessionController(ExpireSessionService expireSessionService) {
        this.expireSessionService = expireSessionService;
    }

    @ModelAttribute("status")
    public String getStatus(ExpireSessionUrlParameters expireSessionParameters) {
        return expireSessionService.expireSessions(expireSessionParameters.getIdle(),
                expireSessionParameters.getPath());
    }

    @RequestMapping("/html/expire")
    public String handle() {
        return "manager";
    }
}
