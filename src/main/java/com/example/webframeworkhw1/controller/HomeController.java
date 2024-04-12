package com.example.webframeworkhw1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/index")
    public String accessIndexPage() {
        return "index";
    }

    @RequestMapping("/main")
    public String accessMainPage() {
        return "main";
    }

    @RequestMapping("/accessDenied")
    public String accessDeniedPage() {
        return "accessDenied";
    }
}
