package com.king.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.king.pojo.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    @Select("select DISTINCT r.role from sys_role r, sys_user_role u where r.id = u.role_id and u.user_id = #{userId}")
    @ResultType(String.class)
    Set<String> selectRolesByUserId(@Param("userId") Long userId);
    
    @Select("select DISTINCT r.id, r.role, r.description, r.alias, r.resource_ids resourceIds, available from sys_role r, sys_user_role u where r.id = u.role_id and u.user_id =#{userId} ")
    List<Role> selectRoleListByUserId(@Param("userId") Long userId); 
    
    @Select("select r.id, r.role, r.description, r.alias, r.resource_ids resourceIds, available from sys_role r ")
    List<Role> selectAllRoles();
    
}