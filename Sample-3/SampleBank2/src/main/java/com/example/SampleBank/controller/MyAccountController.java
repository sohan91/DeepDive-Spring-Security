package com.example.SampleBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAccountController {
    @GetMapping("/myAccount")
    public String myAccount()
    {
        return "All about MyAccounts";
    }
}
