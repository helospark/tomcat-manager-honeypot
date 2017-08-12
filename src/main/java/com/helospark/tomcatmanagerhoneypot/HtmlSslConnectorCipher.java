package com.helospark.tomcatmanagerhoneypot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlSslConnectorCipher {

    @RequestMapping("/html/sslConnectorCiphers")
    public String handle() {
        return "sslConnectorCiphers";
    }
}
