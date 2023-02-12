package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS(credentialid, url, username, key, password, userid) VALUES(#{credentialId},#{url},#{username},#{key},#{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty="credentialId")
    Integer createCredential(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    @Results(id="credentialResult",value={
            @Result(property = "credentialId", column = "credentialid", id=true),
            @Result(property = "userId", column = "userid", id=false)
    })
    List<Credential> findCredentialsByUserId(Integer userId);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username = #{username}, password = #{password}, key = #{key} " +
            "WHERE credentialid = #{credentialId}")
    @Options(useGeneratedKeys = true, keyProperty="credentialId")
    Integer updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredential(Integer credentialId);

}
