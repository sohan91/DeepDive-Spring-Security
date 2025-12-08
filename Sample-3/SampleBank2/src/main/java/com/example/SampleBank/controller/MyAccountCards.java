package com.example.SampleBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAccountCards {
    @GetMapping("/cards")
    public String loans()
    {
        return "Show Cards Lists...";
    }
}
