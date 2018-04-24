package com.king.web.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.king.util.Page;
import com.king.util.PageUtil;


/**
 * 工作流程管理
 * @author wanghuaying
 *
 */
@Controller
@RequestMapping("/workflow")
public class WorkFlowController {

	@Autowired
	protected RepositoryService repositoryService;
	
	/**
	 * 流程列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView processList (HttpServletRequest request,Model model){
		ModelAndView mav = new ModelAndView("/workflow/process-list");
		 /*
	     * 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
	     */
	     List<Object[]> objects = new ArrayList<Object[]>();
	     Page<Object[]> page = new Page<Object[]>(PageUtil.PAGE_SIZE);
	     int[] pageParams = PageUtil.init(page, request);
	     ProcessDefinitionQuery processDefinitionQuery =  repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
	     List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(pageParams[0], pageParams[1]);
	     for (ProcessDefinition processDefinition : processDefinitionList) {
	            String deploymentId = processDefinition.getDeploymentId();
	            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
	            objects.add(new Object[]{processDefinition, deployment});
	        }

	        page.setTotalCount(processDefinitionQuery.count());
	        page.setResult(objects);
	       model.addAttribute("page", page);
	     
	     
		return mav;
	}
	
	
	
}
