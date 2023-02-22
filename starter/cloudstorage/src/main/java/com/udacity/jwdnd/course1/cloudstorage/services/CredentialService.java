package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService){
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }


    @Transactional(rollbackFor = Exception.class)
    public Integer createCredential(Credential credential, Integer userId){

        String encodedKey = Base64.getEncoder().encodeToString(this.getKey());
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        credential.setKey(encodedKey);
        credential.setUserId(userId);
        credential.setPassword(encryptedPassword);

        return credentialMapper.createCredential(credential);
    }

    public List<Credential> findCredentialsByUserId(Integer userId){
        return credentialMapper.findCredentialsByUserId(userId);
    }

    public String findKeyById(Integer id){
        return credentialMapper.findKeyById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer updateCredential(Credential credential){
        String encodedKey = Base64.getEncoder().encodeToString(this.getKey());
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        return credentialMapper.updateCredential(credential);
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCredential(Integer credentialId){
        boolean isDeleteSuccess = false;
        try {
            credentialMapper.deleteCredential(credentialId);
            isDeleteSuccess = true;
        }finally {
            return isDeleteSuccess;
        }
    }

    private byte[] getKey(){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return key;
    }

}
