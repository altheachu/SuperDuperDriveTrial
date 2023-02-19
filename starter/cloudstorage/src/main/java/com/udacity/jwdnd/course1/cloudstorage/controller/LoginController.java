package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/login")
@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(@RequestParam(value = "signupSuccess",required = false) boolean isSuccessSignup, Model model){
        model.addAttribute("signupSuccess",isSuccessSignup);
        return "login";
    }

}
