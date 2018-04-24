package com.king.activiti.identfy;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.junit.Test;

import com.king.activiti.AbstractTest;

public class IdenityGroupUserTest extends AbstractTest{
	
	
	@Test
	public void testGrupAndUser(){
		/**
		 * 创建一个组
		 */
		Group group =identityService.newGroup("adminGroup");
		group.setName("超级管理员");
		group.setType("admin");
		Group group1 =identityService.newGroup("deptGroup");
		group1.setName("技术经理");
		group1.setType("dept");
		Group group2 =identityService.newGroup("pmGroup");
		group2.setName("项目经理");
		group2.setType("pm");
		
		identityService.saveGroup(group);
		identityService.saveGroup(group1);
		identityService.saveGroup(group2);
		
		User user = identityService.newUser("zhangsan");
		
		user.setFirstName("zhang");
		user.setLastName("san");
		identityService.saveUser(user);
		
		User user1 = identityService.newUser("lisi");
		
		user1.setFirstName("li");
		user1.setLastName("si");
		identityService.saveUser(user1);
		
		User user3 = identityService.newUser("admin");
		
		user3.setFirstName("admin");
		user3.setLastName("admin");
		identityService.saveUser(user3);
		identityService.createMembership("admin", "adminGroup");
		identityService.createMembership("zhangsan", "deptGroup");
		identityService.createMembership("lisi", "pmGroup");
		
		
		
		
		
	}
	
	

}
