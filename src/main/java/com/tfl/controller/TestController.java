package com.tfl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("tfl/test")
public class TestController {
    @RequestMapping("/sayHello")
//    @PreAuthorize("hasAnyAuthority('test')")
    @PreAuthorize("@ex.hasAuthority('test')")
    public String sayHello(){
        return "hello";
    }
}
