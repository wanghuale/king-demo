package com.king.activiti.helloword;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.king.activiti.AbstractTest;

public class HellowServiceTest extends AbstractTest{

	
	@Test
    public void testStartProcess() throws Exception {

        RepositoryService repositoryService = processEngine.getRepositoryService();
        String bpmnFileName = "day1/HellowLeave.bpmn";
        repositoryService
                .createDeployment()
                .addInputStream(
                        "HellowLeave.bpmn",
                        this.getClass().getClassLoader()
                                .getResourceAsStream(bpmnFileName)).deploy();

        Long count1 = repositoryService
                .createProcessDefinitionQuery().count();
         System.out.println(count1+"-------------");                       
        //assertEquals("myProcess", processDefinition.getKey());
        //System.out.println(processDefinition.getKey()+"---");
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applyUser", "employee1");
        variables.put("days", 3);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                "myProcess", variables);
        assertNotNull(processInstance);
        System.out.println("pid=" + processInstance.getId() + ", pdid="
                + processInstance.getProcessDefinitionId());

        TaskService taskService = processEngine.getTaskService();
        List<Task> taskOfDeptLeader = taskService.createTaskQuery()
                .taskCandidateGroup("deptLeader").list();
        assertNotNull(taskOfDeptLeader);
        for (Task task : taskOfDeptLeader) {
			System.out.println("领导审批："+task.getName());
			taskService.claim(task.getId(), "deptLeader");
	        variables = new HashMap<String, Object>();
	        variables.put("approved", true);
	        taskService.complete(task.getId(), variables);
	        
	        
		}

        

        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished()
                .count();
        assertEquals(1, count);
    }
	
}
