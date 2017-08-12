package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helospark.tomcatmanagerhoneypot.service.FindLeaksService;

@Controller
public class HtmlFindLeaksController {
    private FindLeaksService findLeaksService;

    public HtmlFindLeaksController(FindLeaksService findLeaksService) {
        this.findLeaksService = findLeaksService;
    }

    @ModelAttribute("status")
    public String getStatus() {
        return findLeaksService.findLeaks();
    }

    @RequestMapping("/html/findleaks")
    public String handle() {
        return "manager";
    }
}
