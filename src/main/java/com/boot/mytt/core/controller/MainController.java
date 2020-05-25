package com.boot.mytt.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Controller
public class MainController {

    @RequestMapping("main")
    public String mainPage() {
        return "demo.html";
    }
}
