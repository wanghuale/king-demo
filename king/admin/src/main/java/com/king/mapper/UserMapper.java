package com.king.mapper;

import java.util.List;

import com.king.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(User record);

    Long insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectUser(User record);
}