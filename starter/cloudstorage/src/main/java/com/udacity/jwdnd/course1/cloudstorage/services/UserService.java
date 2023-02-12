package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService){
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        boolean isUsernameAvailable = true;
        User user = userMapper.findUser(username);
        if(user!=null){
            isUsernameAvailable = false;
        }
        return isUsernameAvailable;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer createUser(User user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        // make salt become a format that hash can understand
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        user.setSalt(encodedSalt);
        user.setPassword(hashedPassword);
        return userMapper.createUser(user);
    }

    public Integer findUserIdByUsername(String username){
        Integer userId = 0;
        User user = userMapper.findUser(username);
        if(user!=null){
            userId = user.getUserId();
        }
        return userId;
    }

}
