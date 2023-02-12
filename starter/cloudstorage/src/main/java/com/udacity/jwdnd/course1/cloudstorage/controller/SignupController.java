package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/signup")
@Controller
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String getSignupPage(){
        return "signup";
    }

    @PostMapping
    public String doSignup(Model model, @ModelAttribute("user") User user){

        boolean signupError = false;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            //The username already exists.
            signupError = true;
        }

        if (signupError == false) {
            int userId = userService.createUser(user);
            if (userId <= 0) {
                //There was an error signing you up. Please try again.
                signupError = false;
            }
        }

        if (signupError == false) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
