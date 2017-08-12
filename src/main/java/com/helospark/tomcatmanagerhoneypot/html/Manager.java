package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Manager {

    @RequestMapping("html")
    public String handle() {
        return "manager";
    }
}
