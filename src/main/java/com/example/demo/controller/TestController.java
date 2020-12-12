package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/t")
    public String test1(Model model){
        model.addAttribute("msg","Hello,Thymeleaf");
        return "test";
    }
}
