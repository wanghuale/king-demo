package com.king.system.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.king.pojo.Role;
import com.king.pojo.User;
import com.king.service.IRoleService;

/**
 * 用户管理
 * @author wanghuaying
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model ){
		List<Role> roles = roleService.selectAllRoles();
		model.addAttribute("roles", roles);
		return "/role/list";
	}
	
	@RequestMapping(value="/create",method = RequestMethod.GET)
	public String create(Model model){
		model.addAttribute("role", new Role());
		return "/role/edit";
	}
	
	@RequestMapping(value="/modfiy",method = RequestMethod.POST)
	public String modify(Role role,RedirectAttributes redirectAttributes){
		roleService.modifyRole(role);
		return "redirect:/role";
	}
	
	@RequestMapping(value="/{id}/edit" ,method = RequestMethod.GET)
	public String edit(Model model,@PathVariable("id") Long id){
		model.addAttribute("role", roleService.getRole(id));
		return "/role/edit";
	}
	
	@RequestMapping(value="/{id}/delete" ,method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id") Long id){
		roleService.delete(id);
		return "redirect:/role";
	}
	

}
