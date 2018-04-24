package com.king.web.manangement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.king.util.Page;
import com.king.util.PageUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IdentityService identityService;

	@RequestMapping(value="/modify")
	public String modify(@RequestParam("userId") String userId,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "email", required = false) String email,
            RedirectAttributes redirectAttributes,Model model){
	    User u = identityService.createUserQuery().userId(userId).singleResult();
		if(u == null ){
			u = identityService.newUser(userId);
			u.setEmail(email);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			if(org.apache.commons.lang3.StringUtils.isNoneBlank(password)){
				u.setPassword(password);
			}
			identityService.saveUser(u);
		}else{
			u.setEmail(email);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			if(org.apache.commons.lang3.StringUtils.isNoneBlank(password)){
				u.setPassword(password);
			}
			identityService.saveUser(u);
			redirectAttributes.addFlashAttribute("msg", "添加的用户名已存在！");
		}
		model.addAttribute("user", u);
		return "/user/edit";
	}
	
	@RequestMapping(value="/list")
	public String list(Model model ,HttpServletRequest request){
		UserQuery query = identityService.createUserQuery();
		Page<User> p = new Page<User>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(p, request);
		List<User> users = query.listPage(pageParams[0], pageParams[1]);
		model.addAttribute("list", users);
		
		return "/user/list";
	}
	
	@RequestMapping(value="/create")
	public String create(Model model){
		return "/user/create";
	}
	
	@RequestMapping(value="/{id}/edit")
	public String edit(@PathVariable("id") String id,Model model){
		User u = null;
		if(org.apache.commons.lang.StringUtils.isNotBlank(id)){
			u = identityService.createUserQuery().userId(id).singleResult();
		}
		model.addAttribute("user", u);
		return "/user/edit";
	}
	
	@RequestMapping(value="/{id}/delete")
	public String delete(@PathVariable("id") String id ,Model model,RedirectAttributes redirectAttributes){

	        identityService.deleteUser(id);
	        redirectAttributes.addFlashAttribute("message", "成功删除用户[" + id + "]");
	        return "redirect:/user/list";
	        
	}
	
	@RequestMapping(value="/{id}/getGroupByUerId")
	@ResponseBody
	public JSONArray getGroupByUerId(@PathVariable("id") String id,Model model ,RedirectAttributes redirectAttributes){
		List<Group> userGroup = identityService.createGroupQuery().groupMember(id).list();
		List<Group> allGroup = identityService.createGroupQuery().list();
		JSONArray list = new JSONArray();
		JSONObject group = null;
		for (Group g : allGroup) {
			group = new JSONObject();
			group.put("groupId", g.getId());
			group.put("groupName", g.getName());
			group.put("checked", false);
			for (Group uGroup : userGroup) {
				if(g.getId().equals(uGroup.getId())){
					group.put("checked", true);
					break;
				}
			}
			list.add(group);
			
		}
		return list;
	}
	
	@RequestMapping(value="/{id}/bindGroup")
	@ResponseBody
	public JSONObject bindGroup(@PathVariable("id") String id ,String ids){
		List<Group> list = identityService.createGroupQuery().list();
		JSONObject obj =new JSONObject();
		for (Group group : list) {
			identityService.deleteMembership(id, group.getId());
		}
		if(!org.springframework.util.StringUtils.isEmpty(ids)){
			String[] gids = ids.split(",");
			for (String str : gids) {
				identityService.createMembership(id, str);
			}
		}
		obj.put("message", "成功");
		obj.put("success", true);
		
		
		return obj;
	}
	
	
	
	
}
