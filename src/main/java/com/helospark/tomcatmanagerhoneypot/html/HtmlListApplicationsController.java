package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlListApplicationsController {

    @ModelAttribute("status")
    public String getStatus() {
        return "OK";
    }

    @RequestMapping("/html/list")
    public String handle() {
        return "manager";
    }
}
