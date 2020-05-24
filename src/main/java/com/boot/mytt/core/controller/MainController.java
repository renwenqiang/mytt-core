package com.boot.mytt.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("main")
    public String mainPage() {
        return "demo.html";
    }
}
