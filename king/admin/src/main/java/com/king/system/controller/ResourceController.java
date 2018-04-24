package com.king.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.king.common.TreeNode;
import com.king.pojo.Resource;
import com.king.pojo.User;
import com.king.service.IResourceService;

/**
 * 用户管理
 * @author wanghuaying
 *
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private IResourceService resourceService;
	
	@ModelAttribute("types")
	public Resource.ResourceType[] resourceTypes() {
		return Resource.ResourceType.values();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model ){
		List<Resource> ls = resourceService.findAll();
		model.addAttribute("resources", ls);
		return "/resource/list";
	}
	
	@RequestMapping(value="/{id}/create",method = RequestMethod.GET)
	public String create(Model model,@PathVariable("id") Long id){
		Resource resource = new Resource();
		if(id !=null ) resource.setParentId(id);
		model.addAttribute("resource", resource);
		return "/resource/edit";
	}
	
	@RequestMapping(value="/modfiy",method = RequestMethod.POST)
	public String modify(Resource resource,RedirectAttributes redirectAttributes){
		resourceService.modify(resource);
		return "redirect:/resource";
	}
	
	@RequestMapping(value="/{id}/edit" ,method = RequestMethod.GET)
	public String edit(Model model,@PathVariable("id") Long id){
		Resource resource = resourceService.getById(id);
		model.addAttribute("resource", resource);
		return "/resource/edit"; 
	}
	
	@RequestMapping(value="/{id}/delete" ,method = RequestMethod.GET)
	@ResponseBody
	public boolean delete(Model model,@PathVariable("id") Long id){
		List<Resource> ls = resourceService.getListByParentId(id);
		if(ls!=null && ls.size() >0){
			return false;
		}
		else{
			resourceService.delete(id);
			return true;
		}	
	}
	
	@RequestMapping(value="/treeNode",method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> resourceTree(Long roleId){
		List<TreeNode> trees = new ArrayList<TreeNode>();
		List<Long> resourceIds = resourceService.selectResourceByRoleId(roleId);
		List<Resource> ls = resourceService.findAll();
		TreeNode treeNode = null;
		for (Resource resource : ls) {
			treeNode = new TreeNode();
			treeNode.setId(resource.getId());
			treeNode.setName(resource.getName());
			treeNode.setpId(resource.getParentId());
			treeNode.setChecked(false);
			if(resourceIds !=null && resourceIds.size() >0){
				if(resourceIds.contains(resource.getId())){
					treeNode.setChecked(true);
				}
			}
			trees.add(treeNode);
		}
		
		return trees;
	}

}
