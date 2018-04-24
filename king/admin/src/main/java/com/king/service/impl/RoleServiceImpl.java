package com.king.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.mapper.ResourceMapper;
import com.king.mapper.RoleMapper;
import com.king.mapper.RoleResourceMapper;
import com.king.pojo.Role;
import com.king.pojo.RoleResource;
import com.king.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	@Override
	public List<Role> selectAllRoles() {
		List<Role> ls = roleMapper.selectAllRoles();
		for (Role role : ls) {
			role.setResources(resourceMapper.selectByRoleId(role.getId()));
		}
		return roleMapper.selectAllRoles();
	}
	@Override
	public void modifyRole(Role role) {
		role.setDescription(role.getRole());
		if(role.getId() !=null){
			roleMapper.updateByPrimaryKeySelective(role);
			roleResourceMapper.deleteByRole(role.getId());
		}else{   
			roleMapper.insertSelective(role);
		}
		if(StringUtils.isNotBlank(role.getResourceIds())){
			String[] ls = role.getResourceIds().split(",");
			RoleResource r = null;
			for (String str : ls) {
				r =new RoleResource();
				r.setResourceId(Long.valueOf(str));
				r.setRoleId(role.getId());
				roleResourceMapper.insertSelective(r);
			}
		}
	}
	@Override
	public Role getRole(Long roleId) {
		Role role = roleMapper.selectByPrimaryKey(roleId);
		if(role !=null){
			role.setResources(resourceMapper.selectByRoleId(roleId));
		}
		return role;
	}
	@Override
	public void delete(Long roleId) {
		roleMapper.deleteByPrimaryKey(roleId);
		roleResourceMapper.deleteByRole(roleId);
	}
	
	
	

	
	
}
