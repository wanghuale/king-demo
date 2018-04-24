package com.king.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.king.bind.annotation.CurrentUser;
import com.king.pojo.Resource;
import com.king.pojo.User;
import com.king.service.IResourceService;
import com.king.service.IUserService;

/**
 * 用户登录
 * @author wanghuaying
 *
 */
@Controller
public class indexController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IResourceService resourceService;
	@RequestMapping(value="/",method= RequestMethod.GET)
	public String index( Model model) {
		User loginUser  =(User)SecurityUtils.getSubject().getPrincipal();
		User user = userService.findByLoginName(loginUser.getLoginName());
		List<Resource> menus = resourceService.findMenus(user.getId());
		model.addAttribute("menus", menus);
		return "index";
	}
	@RequestMapping(value="/welcome")
	public String welcome(HttpServletRequest req, Model model){
		return "welcome";
	}
}
