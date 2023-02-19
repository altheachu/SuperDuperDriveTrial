package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/note")
@Controller
public class NoteController {

    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService){
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/createUpdate")
    public String doSetNote(Model model, Authentication authentication, Note note){
        // find userId
        Integer userId = userService.findUserIdByUsername(authentication.getName());

        if(userId > 0){
            if(note.getNoteId()==null||note.getNoteId().equals("")){
                if(noteService.createNote(note, userId) > 0){
                    model.addAttribute("success", true);
                }else{
                    model.addAttribute("error", true);
                    model.addAttribute("errorMsg", "create note failed");
                }

            }else{
                Integer noteId = noteService.updateNote(note);
                if(noteId > 0){
                    model.addAttribute("success", true);
                }else{
                    model.addAttribute("error", true);
                    model.addAttribute("errorMsg", "update note failed");
                }
            }
        }else{
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "found no userId");
        }

        return "/result";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(Model model, @PathVariable("noteId") Integer noteId) {
        boolean deleteResult = noteService.deleteNote(noteId);
        if(deleteResult){
            model.addAttribute("success", true);
        }else{
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "delete note failed");
        }
        return "/result";
    }
}
