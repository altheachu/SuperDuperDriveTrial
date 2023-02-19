package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RequestMapping("/file")
@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService){
        this.fileService = fileService;
        this.userService = userService;
    }

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

    @GetMapping("/delete/{fileId}")
    public String deleteFile(Model model, @PathVariable("fileId") Integer fileId){
        boolean deleteResult = fileService.deleteFileById(fileId);
        if(deleteResult==true){
            model.addAttribute("success", true);
        }else{
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "delete file failed");
        }
        return "/result";
    }

    @GetMapping("/view/{fileId}")
    public String viewFile(@PathVariable("fileId") Integer fileId, HttpServletResponse response, Model model) throws IOException {

        boolean isViewSuccess = false;
        File file = fileService.findFileById(fileId);
        byte[] fileData = file.getFileData();

        response.setContentType(file.getContentType());
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getFilename());
        response.setContentLength(fileData.length);

        OutputStream os = response.getOutputStream();
        try {
            os.write(fileData, 0, fileData.length);
            isViewSuccess = true;
        } catch (Exception ex){
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "view/download file failed");
        }finally {
            os.close();
            if(isViewSuccess){
                return "/home";
            }else{
                return "result";
            }
        }
    }
}
