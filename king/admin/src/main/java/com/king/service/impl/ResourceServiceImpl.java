package com.king.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.mapper.ResourceMapper;
import com.king.mapper.RoleResourceMapper;
import com.king.pojo.Resource;
import com.king.service.IResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {

	@Autowired 
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	@Override
	public List<Resource> findMenus(Long userId) {
		List<Resource> allResources = resourceMapper.selectAll();
		Set<String> permissions = resourceMapper.selectByPermissionByUserId(userId);
		List<Resource> menus = new ArrayList<Resource>();
		
		for (Resource resource : allResources) {
			if (resource.isRootNode()) {
				continue;
			}
			if (!resource.getType().equals(Resource.ResourceType.menu.toString())) {
				continue;
			}
			if (!hasPermission(permissions, resource)) {
				continue;
			}
			menus.add(resource);
		}
		return menus;
	}
	
	private boolean hasPermission(Set<String> permissions,Resource resource){
		if (StringUtils.isEmpty(resource.getPermission())) {
			return true;
		}
		for (String str : permissions) {
			WildcardPermission p1 = new WildcardPermission(str);
			WildcardPermission p2 = new WildcardPermission(resource.getPermission());
			if (p1.implies(p2) || p2.implies(p1)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Resource> findAll() {
		return resourceMapper.selectAll();
	}

	@Override
	public List<Long> selectResourceByRoleId(Long roleId) {
		if(roleId !=null)
			return roleResourceMapper.selectByRoleId(roleId);
		else
			return null;
	}

	@Override
	public void modify(Resource resource) {
		Resource parent = resourceMapper.selectByPrimaryKey(resource.getParentId());
		resource.setParentIds(parent.makeSelfAsParentIds());
		if(resource.getId() !=null ){
			resourceMapper.updateByPrimaryKeySelective(resource);
		}else{
			resourceMapper.insertSelective(resource);
		}
	}

	@Override
	public void delete(Long id) {
		resourceMapper.deleteByPrimaryKey(id);
		roleResourceMapper.deleteByResource(id);
	}

	@Override
	public Resource getById(Long id) {
		return resourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Resource> getListByParentId(Long id) {
		
		return resourceMapper.selectByParentId(id);
	}
	
	
	
	
	
	
}
