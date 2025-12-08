package com.example.SampleBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleBankController {
    @GetMapping("/sayHello")
    public String wish()
    {
        return "welcome to SampleBanks";
    }
}
