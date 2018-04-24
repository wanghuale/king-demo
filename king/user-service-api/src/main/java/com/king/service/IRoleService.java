package com.king.service;

import java.util.List;

import com.king.pojo.Role;

public interface IRoleService {
	
	public List<Role> selectAllRoles();
	
	public void modifyRole(Role role);
	
	public Role getRole(Long roleId);
	
	public void delete(Long roleId);

}
