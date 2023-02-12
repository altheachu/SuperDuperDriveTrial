package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper){
        this.noteMapper = noteMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer createNote(Note note, Integer userId){
        note.setUserId(userId);
        return noteMapper.createNote(note);
    }

    public List<Note> findNotesByUserId(Integer userId){
        return noteMapper.findNoteByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer updateNote(Note note){
        return noteMapper.updateNote(note);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    };

}
