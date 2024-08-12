package io.whatap.ldap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String indexView() {
        return "index";
    }
    @GetMapping("/create")
    public String createView() {
        return "create";
    }

    @PostMapping("/create")
    public String createAccount() {
        return "redirect://index";
    }
}
