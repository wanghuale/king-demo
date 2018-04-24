package com.king.web.manangement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.king.util.Page;
import com.king.util.PageUtil;

@Controller
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	IdentityService identityService;

	@RequestMapping(value="/modify")
	public String modify(@RequestParam("groupId") String groupId,
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            RedirectAttributes redirectAttributes,Model model){
	    Group u = identityService.createGroupQuery().groupId(groupId).singleResult();
		if(u == null ){
			u = identityService.newGroup(groupId);
			u.setName(name);
			u.setType(type);
			identityService.saveGroup(u);
		}else{
			u.setName(name);
			u.setType(type);
			identityService.saveGroup(u);
			redirectAttributes.addFlashAttribute("msg", "添加的组名已存在！");
		}
		model.addAttribute("group", u);
		return "/group/edit";
	}
	
	@RequestMapping(value="/list")
	public String list(Model model ,HttpServletRequest request){
		GroupQuery query = identityService.createGroupQuery();
		Page<Group> p = new Page<Group>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(p, request);
		List<Group> groups = query.listPage(pageParams[0], pageParams[1]);
		model.addAttribute("list", groups);
		
		return "/group/list";
	}
	
	@RequestMapping(value="/create")
	public String create(Model model){
		return "/group/create";
	}
	
	@RequestMapping(value="/{id}/edit")
	public String edit(@PathVariable("id") String id,Model model){
		Group u = null;
		if(org.apache.commons.lang.StringUtils.isNotBlank(id)){
			u = identityService.createGroupQuery().groupId(id).singleResult();
		}
		model.addAttribute("group", u);
		return "/group/edit";
	}
	
	@RequestMapping(value="/{id}/delete")
	public String delete(@PathVariable("id") String id ,Model model,RedirectAttributes redirectAttributes){

	        identityService.deleteUser(id);
	        redirectAttributes.addFlashAttribute("message", "成功删除组[" + id + "]");
	        return "redirect:/group/list";
	        
	}
	
}
