package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    @Results(id="fileResult", value={
            @Result(property = "contentType", column = "contenttype", id=false),
            @Result(property = "fileSize", column = "filesize", id=false),
            @Result(property = "userId", column = "userid", id=false),
            @Result(property = "fileData", column = "filedata", id=false),
            @Result(property = "fileId", column = "fileid", id=true)
    })
    public List<File> isFilenameExist(String filename);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    @Results(id="fileResultByUserId", value={
            @Result(property = "contentType", column = "contenttype", id=false),
            @Result(property = "fileSize", column = "filesize", id=false),
            @Result(property = "userId", column = "userid", id=false),
            @Result(property = "fileData", column = "filedata", id=false),
            @Result(property = "fileId", column = "fileid", id=true)
    })
    public List<File> findFilesByUserId(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    @Results(id="fileResultById", value={
            @Result(property = "contentType", column = "contenttype", id=false),
            @Result(property = "fileSize", column = "filesize", id=false),
            @Result(property = "userId", column = "userid", id=false),
            @Result(property = "fileData", column = "filedata", id=false),
            @Result(property = "fileId", column = "fileid", id=true)
    })
    public File findFileById(Integer flleId);

    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    @Insert("INSERT INTO FILES(FILENAME, CONTENTTYPE, FILESIZE, USERID, FILEDATA) VALUES (#{filename},#{contentType},#{fileSize},#{userId},#{fileData})")
    public Integer createFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    public void deleteFileById(Integer fileId);
}
