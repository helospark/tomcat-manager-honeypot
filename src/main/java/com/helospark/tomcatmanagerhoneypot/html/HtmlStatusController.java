package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlStatusController {

    @RequestMapping("/status")
    public String handle() {
        return "status";
    }

    @RequestMapping("/status/all")
    public String handleAll() {
        return "status";
    }
}
