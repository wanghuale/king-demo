package com.king.service;

import java.util.List;
import java.util.Set;

import com.king.pojo.User;

public interface IUserService {
	
	/**
	 * 更新 用户登录信息
	 * 
	 */
	void updateLoginInfo(User user ,String ip);
	
	/**
	 * 根据登录名获取用户对象
	 * 
	 */
	User findByLoginName(String loginName);
	
	/**
	 * 根据登录名获取角色
	 * @param loginName
	 * @return
	 */
	Set<String> findRoles(String loginName);
	
	/**
	 * 根据登录名获取资源
	 * @param loginName
	 * @return
	 */
	Set<String> findPermissions(String loginName);
	
	/**
	 * 获取用户列表
	 * 
	 */
	List<User> findUserList();
	
	/**
	 * 根据用户id获取用户信息
	 * 
	 */
	User selectUserById(Long userId);
	
	/**
	 * 维护用户
	 * 
	 */
	void modifyUser(User user);
	
	/**
	 * 删除用户id
	 */
	void delete(Long id);
	
	

}
