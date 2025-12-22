package com.eazybytes.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String displayLoginPage(Model model,@RequestParam(name = "error",required = false)String errorMsg,
                                   @RequestParam(name = "logout",required = false)String logoutMsg) {

        String message = null;
        if(errorMsg != null)
        {
            message = "User or Password is incorrect!!";
        }
        if(logoutMsg != null)
        {
            message = "User have been Successfully logged out!!";
        }
        model.addAttribute("error",message);
        return "login.html";
    }

}