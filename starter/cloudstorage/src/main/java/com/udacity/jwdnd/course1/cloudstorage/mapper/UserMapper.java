package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USERS (userid, username, salt, password, firstname, lastname) VALUES (#{userId}, #{username}, #{salt},#{password},#{firstname},#{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int createUser(User user);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    @Results(id="userResult", value={
            @Result(property = "userId", column = "userid", id=true)
    })
    User findUser(String username);

    @Update("UPDATE USERS SET username = #{username}, salt = #{salt}, password = #{password}, firstname = #{firstname}, lastname = #{lastname} WHERE userid = #{userId}")
    int updateUser(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{userId}")
    void deleteUser(Integer userId);

}
