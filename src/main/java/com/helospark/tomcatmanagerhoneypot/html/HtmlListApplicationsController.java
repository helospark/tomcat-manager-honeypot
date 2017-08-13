package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;

@Controller
@HtmlExceptionHandler
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
