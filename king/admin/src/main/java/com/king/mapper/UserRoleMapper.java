package com.king.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.king.pojo.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    @Select("select distinct role_id as roleId from sys_user_role where user_id =#{userId} ")
    @ResultType(Long.class)
    List<Long> selectRoleIdByUserId(@Param("userId") Long userId);
    
    @Select("delete from sys_user_role where user_id = #{userId}")
    void deleteByUserId(@Param("userId") Long userId);
}