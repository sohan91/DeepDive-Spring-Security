package com.example.SampleBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {
    @GetMapping("/loans")
    public String loans()
    {
        return "All about Loans";
    }
}
