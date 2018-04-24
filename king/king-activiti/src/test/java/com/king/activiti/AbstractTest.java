package com.king.activiti;
/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import org.activiti.engine.*;
import org.activiti.engine.test.ActivitiRule;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;


/**
 * 抽象测试基类
 *
 * @author wanghuaying
 */
public abstract class AbstractTest {

    /**
     * 专门用于测试套件
     */
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti.cfg.xml");

    /**
     * 创建流程实例
     */
    protected ProcessEngine processEngine;
    /**
     * 流程资源部署
     */
    protected RepositoryService repositoryService;
    /**
     * 流程的运行启动
     */
    protected RuntimeService runtimeService;
    
    /**
     * 任务
     */
    protected TaskService taskService;
    
    /**
     * 流程历史服务组件
     */
    protected HistoryService historyService;
    /**
     * 用户认证
     */
    protected IdentityService identityService;
    
    /**
     * 流程引擎的维护管理
     */
    protected ManagementService managementService;
    /**
     * 表单管理
     */
    protected FormService formService;

    /**
     * 开始测试
     */
    @BeforeClass
    public static void setUpForClass() throws Exception {
        System.out.println("++++++++ 开始测试 ++++++++");
    }

    /**
     * 结束测试
     */
    @AfterClass
    public static void testOverForClass() throws Exception {
        System.out.println("-------- 结束测试 --------");
    }

    /**
     * 初始化变量
     */
    @Before
    public void setUp() throws Exception {
        processEngine = activitiRule.getProcessEngine();
        repositoryService = activitiRule.getRepositoryService();
        runtimeService = activitiRule.getRuntimeService();
        taskService = activitiRule.getTaskService();
        historyService = activitiRule.getHistoryService();
        identityService = activitiRule.getIdentityService();
        managementService = activitiRule.getManagementService();
        formService = activitiRule.getFormService();
    }

}
