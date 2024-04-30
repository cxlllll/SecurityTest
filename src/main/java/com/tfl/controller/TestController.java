package com.tfl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("tfl/test")
public class TestController {
    @RequestMapping("/sayHello")
    public String sayHello(){
        return "hello";
    }
}
