package com.king.system.controller;

import java.util.List;

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
import com.king.service.IUserService;
import com.king.service.impl.PasswordHelper;

/**
 * 用户管理
 * @author wanghuaying
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private PasswordHelper passwordHelper;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model ){
		List<User> users = userService.findUserList();
		model.addAttribute("users",users);
		return "/user/list";
	}
	
	@RequestMapping(value="/create",method = RequestMethod.GET)
	public String create(Model model){
		List<Role> roles = roleService.selectAllRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("user", new User());
		return "/user/edit";
	}
	
	@RequestMapping(value="/modify",method = RequestMethod.POST)
	public String modify(User user,RedirectAttributes redirectAttributes){
		userService.modifyUser(user);
		return "redirect:/user";
	}
	
	@RequestMapping(value="/{id}/eidt" ,method = RequestMethod.GET)
	public String edit(Model model,@PathVariable("id") Long id){
		User user = userService.selectUserById(id);
		model.addAttribute("user", user);
		List<Role> roles = roleService.selectAllRoles();
		model.addAttribute("roles", roles);
		return "/user/edit";
	}
	
	@RequestMapping(value="/{id}/delete" ,method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id") Long id){
		userService.delete(id);
		
		return "redirect:/user";
	}
	

}
