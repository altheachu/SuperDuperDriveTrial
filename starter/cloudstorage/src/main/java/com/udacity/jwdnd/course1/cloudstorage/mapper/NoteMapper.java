package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES(noteid, notetitle, notedescription, userid) VALUES (#{noteId},#{noteTitle},#{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer createNote(Note note);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    @Results(id="noteResult", value={
            @Result(property = "userId", column = "userid", id=false),
            @Result(property = "noteTitle", column = "notetitle", id=false),
            @Result(property = "noteDescription", column = "notedescription", id=false),
            @Result(property = "noteId", column = "noteid", id=true)
    })
    Note findNote(Integer noteId);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    @Results(id="noteResultUserId", value={
            @Result(property = "userId", column = "userid", id=false),
            @Result(property = "noteTitle", column = "notetitle", id=false),
            @Result(property = "noteDescription", column = "notedescription", id=false),
            @Result(property = "noteId", column = "noteid", id=true)
    })
    List<Note> findNoteByUserId(Integer userId);

    @Select("SELECT * FROM NOTES")
    @Results(id="noteAllResults", value={
            @Result(property = "userId", column = "userid", id=false),
            @Result(property = "noteTitle", column = "notetitle", id=false),
            @Result(property = "noteDescription", column = "notedescription", id=false),
            @Result(property = "noteId", column = "noteid", id=true)
    })
    List<Note> findAllNotes();

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);
}
