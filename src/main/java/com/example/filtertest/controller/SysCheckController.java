package com.example.filtertest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SysCheckController {

    @GetMapping("/syscheck")
    public String syscheck(HttpServletRequest request) {
        return String.format("request path: %s, execute syscheck", request.getRequestURI());
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
