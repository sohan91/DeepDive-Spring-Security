package com.example.SampleBank.controller;

import com.example.SampleBank.model.Customer;
import com.example.SampleBank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody Customer customer)
    {
        try
        {
            String pwd = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(pwd);
            Customer savCustomer = repository.save(customer);
            if(savCustomer.getId()>0)
            {
                return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Registered...");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("can't Registered User...");
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception raised");
        }
    }
}
