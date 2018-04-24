package com.king.service;

import java.util.List;

import com.king.pojo.Resource;

public interface IResourceService {
	
	/**
	 * 根据用户id 获取资源权限
	 * @param userId
	 * @return
	 */
	List<Resource> findMenus(Long userId);
	
	/**
	 * 获取所以资源
	 * 
	 */
	public List<Resource> findAll();
	
	/**
	 * 根据角色id 获取资源id 
	 */
	public List<Long> selectResourceByRoleId(Long roleId);
	
	/***
	 * 修改 资源
	 * 
	 */
	public void modify(Resource resource);

	/**
	 * 删除 资源
	 * 
	 */
	public void delete(Long id);
	
	/**
	 * 获取资源
	 * @param id
	 * @return
	 */
	public Resource getById(Long id);
	
	public List<Resource> getListByParentId(Long id);

}
