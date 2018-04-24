package com.king.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.king.pojo.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
    
    @Select("select distinct s.permission from "
    		+ "sys_user u ,sys_user_role ur,"
    		+ "sys_role r ,sys_resource s ,"
    		+ "sys_role_resource rs where "
    		+ "u.id = ur.user_id "
    		+ "and ur.role_id = r.id and "
    		+ "r.id=rs.role_id and "
    		+ "s.id =rs.resource_id "
    		+ " and u.id = #{userId}")
    @ResultType(String.class)
    Set<String> selectByPermissionByUserId(@Param("userId") Long userId);
    
    @Select("select id, name, type, url, permission, parent_id as parentId, parent_ids as parentIds, available from sys_resource order by concat(parent_ids, id) asc")
    List<Resource> selectAll();
    @Select("select distinct resource.id, resource.name, resource.type, resource.url, resource.permission, resource.parent_id as parentId, resource.parent_ids as parentIds, resource.available from sys_resource resource,sys_role_resource role where role.role_id=#{roleId} order by concat(resource.parent_ids, resource.id) asc")
    List<Resource> selectByRoleId(@Param("roleId") Long roleId);
    @Select("select id, name, type, url, permission, parent_id as parentId, parent_ids as parentIds, available from sys_resource where parent_id =#{id} order by concat(parent_ids, id) asc")
    List<Resource> selectByParentId(@Param("id") Long id);
    
    
}