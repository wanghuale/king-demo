package com.king.activiti.timer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.JobQuery;
import org.activiti.engine.test.Deployment;

public class TimerServiceTest extends PluggableActivitiTestCase{

	@Deployment(resources = "day1/timerProcess.bpmn")
	public void testTimer(){
	
		
		
		JobQuery job = managementService.createJobQuery();
		System.out.println(job.count()+"---");
		//assertEquals(1, job.count());
		// 模拟时间5分钟之后
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        System.out.println(".... start ...." + sdf.format(new Date()));
       // ProcessEngineConfiguration clock = Context.getProcessEngineConfiguration();
        //Context.getProcessEngineConfiguration().getClock().setCurrentTime(new Date(System.currentTimeMillis() + ((50 * 60 * 1000) + 5000)));
        //waitForJobExecutorToProcessAllJobs(5000L, 1L);
        //System.out.println(".... end ...." + sdf.format(new Date()));

       // assertEquals(0, job.count());

        // 检查是否启动了流程实例
        long count = runtimeService.createProcessInstanceQuery().processDefinitionKey("timerStartEvent1").count();
        assertEquals(1, count);
		
		
		
	}
	
}
