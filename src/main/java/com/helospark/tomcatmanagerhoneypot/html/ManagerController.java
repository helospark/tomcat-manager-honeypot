package com.helospark.tomcatmanagerhoneypot.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation.HtmlExceptionHandler;

@Controller
@HtmlExceptionHandler
public class ManagerController {

    @RequestMapping("html")
    public String handle() {
        return "manager";
    }
}
