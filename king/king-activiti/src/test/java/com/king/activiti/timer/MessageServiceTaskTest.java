package com.king.activiti.timer;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

public class MessageServiceTaskTest extends PluggableActivitiTestCase{

	@Deployment(resources="day1/messageStartTast.bpmn")
	public void  testMessageStart(){
		ProcessInstance processInstance = runtimeService.startProcessInstanceByMessage("MSG_hua_01");
		System.out.println(processInstance);
		
	}
	
}
