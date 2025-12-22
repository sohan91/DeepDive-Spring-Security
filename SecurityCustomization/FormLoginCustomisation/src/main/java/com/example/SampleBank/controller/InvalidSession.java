package com.example.SampleBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvalidSession {
    @GetMapping("/invalidSession")
    public String raise()
    {
        return "session failed";
    }
}
