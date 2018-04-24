package com.king.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.king.pojo.RoleResource;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
    
    @Select("select DISTINCT resource_id as resourceId from sys_role_resource where role_id = #{roleId}")
    @ResultType(Long.class)
    List<Long> selectByRoleId(@Param("roleId") Long roleId);
    @Select("delete from sys_role_resource  where role_id =#{roleId}")
    void deleteByRole(@Param("roleId") Long roleId);
    @Select("delete from sys_role_resource where resource_id =#{resourceId}")
    void deleteByResource(@Param("resourceId") Long resourceId);
    
}