package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RequestMapping("/file")
@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService){
        this.fileService = fileService;
        this.userService = userService;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/upload")
    public String uploadFile(Model model, Authentication authentication, @RequestParam("fileUpload") MultipartFile file) {

        Integer userId = userService.findUserIdByUsername(authentication.getName());

        if(userId > 0){
            if(!file.isEmpty()){
                if(fileService.isFilenameExist(file)){
                    model.addAttribute("error", true);
                    model.addAttribute("errorMsg", "filename exist");
                }else{
                    try {
                        if(fileService.uploadFile(file, userId) > 0){
                            model.addAttribute("success", true);
                        }else{
                            model.addAttribute("error", true);
                            model.addAttribute("errorMsg", "upload file failed");
                        }
                    } catch (IOException e) {
                        model.addAttribute("error", true);
                        model.addAttribute("errorMsg", "upload file failed");
                    }
                }
            }else{
                model.addAttribute("error", true);
                model.addAttribute("errorMsg", "empty file");
            }
        }else{
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "found no userId");
        }

        return "result";
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/delete/{field}")
    public String deleteFile(@PathVariable("field") Integer field){
        return "result";
    }
}
