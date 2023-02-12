package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.stereotype.Service;
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

    public Integer uploadFile(MultipartFile file, Integer userId) throws IOException {
        File newFile = new File();
        newFile.setUserId(userId);
        newFile.setFileData(file.getBytes());
        newFile.setFileSize(String.valueOf(file.getSize()));
        newFile.setContentType(file.getContentType());
        newFile.setFilename(file.getOriginalFilename());
        // TODO
        return 1;
    }


}
