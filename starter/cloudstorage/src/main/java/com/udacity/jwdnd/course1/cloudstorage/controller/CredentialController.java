package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/credential")
@Controller
public class CredentialController {

    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService){
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping("/createUpdate")
    public String doSetCredential(Model model, Authentication authentication, Credential credential){

        Integer userId = userService.findUserIdByUsername(authentication.getName());
        if(userId > 0){
            if(credential.getCredentialId()==null||credential.getCredentialId().equals("")){
                if(credentialService.createCredential(credential, userId) > 0){
                    model.addAttribute("success", true);
                }else{
                    model.addAttribute("error", true);
                    model.addAttribute("errorMsg", "create credential failed");
                }

            }else{
                Integer credentialId = credentialService.updateCredential(credential);
                if(credentialId > 0){
                    model.addAttribute("success", true);
                }else{
                    model.addAttribute("error", true);
                    model.addAttribute("errorMsg", "update credential failed");
                }
            }
        }else{
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "found no userId");
        }
        return "result";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(Model model, @PathVariable("credentialId") Integer credentialId){
        boolean isDeleteSuccess = credentialService.deleteCredential(credentialId);
        if(isDeleteSuccess){
            model.addAttribute("success",true);
        }else{
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "delete credential failed");
        }
        return "result";
    }
}
