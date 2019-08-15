package com.bpmn.test.BPMN;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
//import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.task.Task;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ProcessTestCase {
	
	@Rule
	//public ProcessEngineRule processEngine= new ProcessEngineRule();
	//public ProcessEngineRule rule = new ProcessEngineRule(new StandaloneInMemProcessEngineConfiguration().buildProcessEngine());
	@ClassRule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();
	
	@Deployment(resources="manualpayment.bpmn")
	@Test
	public void testHappyPath() throws Exception {
		ProcessEngine processEngine=rule.getProcessEngine();
		//ProcessEngine processEngines = ProcessEngines.getDefaultProcessEngine();
		//BpmnModelInstance instance=Bpmn.createExecutableProcess("manualpayment").startEvent("StartEvent_1").done();
		//File file = new File("C://Users//girish.r//workspace//BPMN//src//main//resource//manualpayment.bpmn");
		//BpmnModelInstance modelInstance = Bpmn.readModelFromFile(file);*/
		//String name=processEngines.getRepositoryService().createDeployment().addClasspathResource("C:\\Users\\girish.r\\workspace\\BPMN\\src\\main\\resource\\manualpayment.bpmn").deploy().getName();
		//String name=processEngine.getRepositoryService().createDeployment().addString("manualpayment.bpmn", modelInstance.toString()).deploy().getName();
		//String name=processEngine.getRepositoryService().createDeployment().source("C:\\Users\\girish.r\\workspace\\BPMN\\src\\main\\resource\\manualpayment.bpmn").deploy().getName();
		//System.out.println("name is "+name);
		/*org.camunda.bpm.engine.repository.Deployment test=processEngine.getRepositoryService().createDeployment().addModelInstance("manualpayment.bpmn", arg1).deploy();
		System.out.println(test.getSource());*/
		ProcessInstance pi=processEngine.getRuntimeService().startProcessInstanceByKey("manualpayment");
		List<HistoricActivityInstance> historicActivityInstance= processEngine.getHistoryService().createHistoricActivityInstanceQuery().processDefinitionId(pi.getProcessDefinitionId()).list();
		assertThat(historicActivityInstance.size()>0);
		assertThat(pi).task("Task_02qb9cs").isAssignedTo("john");
		List<Task> tasks=processEngine.getTaskService().createTaskQuery().taskAssignee("john").list();
		assertEquals(1,tasks.size());
		assertEquals("Task_02qb9cs", tasks.get(0).getTaskDefinitionKey());
		processEngine.getTaskService().complete(tasks.get(0).getId());
		assertThat(pi).isStarted().isEnded();
	}
}
