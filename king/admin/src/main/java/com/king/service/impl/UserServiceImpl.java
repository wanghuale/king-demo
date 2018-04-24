package com.king.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.mapper.ResourceMapper;
import com.king.mapper.RoleMapper;
import com.king.mapper.UserMapper;
import com.king.mapper.UserRoleMapper;
import com.king.pojo.Role;
import com.king.pojo.User;
import com.king.pojo.UserRole;
import com.king.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
    private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Override
	public void updateLoginInfo(User user, String ip) {
		
	}

	@Override
	public User findByLoginName(String loginName) {
		User user =new User();
		user.setLoginName(loginName);
		List<User> list = userMapper.selectUser(user);
		return list!=null&& list.size()>0?list.get(0):null;
	}

	@Override
	public Set<String> findRoles(String loginName) {
		User user = findByLoginName(loginName);
		Set<String> roles = new HashSet<String>();
		if(user !=null ){
			roles = roleMapper.selectRolesByUserId(user.getId());
		}
		return roles;
	} 

	@Override
	public Set<String> findPermissions(String loginName) {
		User user = findByLoginName(loginName);
		Set<String> permissions = new HashSet<String>();
		if(user !=null){
			permissions = resourceMapper.selectByPermissionByUserId(user.getId());
		}
		return permissions;
	}

	@Override
	public List<User> findUserList() {
		List<User> users = userMapper.selectUser(new User());
		for (User u : users) {
			List<Role> roles = roleMapper.selectRoleListByUserId(u.getId());
			u.setRoles(roles);
		}
		
		return users;
	}

	@Override
	public User selectUserById(Long userId) {
		
		User user = userMapper.selectByPrimaryKey(userId);
		
		if(user !=null){
			user.setRoles(roleMapper.selectRoleListByUserId(userId));
		}
		return user;
	}

	@Override
	public void modifyUser(User user) {
		if(user.getId() ==null){
			user.setUserName(user.getLoginName());
			passwordHelper.encryptPassword(user);
			userMapper.insertSelective(user);
		}else{
			 userMapper.updateByPrimaryKey(user);
		}
		
		userRoleMapper.deleteByUserId(user.getId());
		UserRole userRole =null;
		List<Long> roles = user.getRoleIds();
		for (Long roleId : roles) {
			userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(user.getId());
			userRoleMapper.insert(userRole);
		}
		
		
	}

	@Override
	public void delete(Long id) {
			userMapper.deleteByPrimaryKey(id);
			userRoleMapper.deleteByUserId(id);
	}
	
	
	
	
	
	
	
}
