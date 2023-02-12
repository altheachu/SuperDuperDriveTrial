package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;
    private UserService userService;

    private CredentialService credentialService;
    public HomeController(NoteService noteService, UserService userService, CredentialService credentialService){
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
    }
    @GetMapping
    public String getHomePage(Model model, Authentication authentication){

        Integer userId = userService.findUserIdByUsername(authentication.getName());
        model.addAttribute("notes", noteService.findNotesByUserId(userId));
        model.addAttribute("credentials",credentialService.findCredentialsByUserId(userId));
        return "home";
    }



}
