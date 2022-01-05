package com.icia.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        System.out.println("index 호출");
        System.out.println("MainController.index");
        return "index";
    }
}
