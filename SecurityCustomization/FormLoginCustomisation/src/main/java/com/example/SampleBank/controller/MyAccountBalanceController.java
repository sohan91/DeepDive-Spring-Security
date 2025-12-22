package com.example.SampleBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAccountBalanceController {
    @GetMapping("/balance")
    public String balance()
    {
        return "Checking AccountBalance....";
    }
}
