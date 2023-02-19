package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper){
        this.fileMapper = fileMapper;
    }

    public boolean isFilenameExist(MultipartFile file){
        boolean isFilenameExist = false;
        List<File> files = fileMapper.isFilenameExist(file.getOriginalFilename());
        if(files!=null && files.size() > 0){
            isFilenameExist = true;
        }
        return isFilenameExist;
    }
    @Transactional(rollbackFor = Exception.class)
    public Integer uploadFile(MultipartFile file, Integer userId) throws IOException {
        File newFile = new File();
        newFile.setUserId(userId);
        newFile.setFileData(file.getBytes());
        newFile.setFileSize(String.valueOf(file.getSize()));
        newFile.setContentType(file.getContentType());
        newFile.setFilename(file.getOriginalFilename());
        return fileMapper.createFile(newFile);
    }

    public List<File> findFilesByUserId(Integer userId){
        return fileMapper.findFilesByUserId(userId);
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFileById(Integer fileId){
        boolean deleteResult = false;
        try{
            fileMapper.deleteFileById(fileId);
            deleteResult = true;
        }finally {
            return deleteResult;
        }
    }

    public File findFileById(Integer fileId){
        return fileMapper.findFileById(fileId);
    }
}
