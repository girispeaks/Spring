package com.bpmntest.bdd.bpmn_model_test;

import static com.google.common.base.Preconditions.checkArgument;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.fluent.FluentProcessInstance;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.cfg.MostUsefulProcessEngineConfiguration;
import org.camunda.bpm.engine.test.fluent.FluentProcessEngineTests;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.hamcrest.collection.IsEmptyCollection;
//import org.camunda.bpm.test.CamundaSupport;
//import org.camunda.bpm.engine.ProcessEngine;
//import org.camunda.bpm.engine.test.cfg.MostUsefulProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

public class BaseCamunda {
	
		private static BaseCamunda instance;
		private static final Logger logger = LoggerFactory.getLogger(BaseCamunda.class);
		private final Set<String> deploymentIds = Sets.newHashSet();
		private ProcessEngine processEngine;
		private Date startTime;
	    private RepositoryService repServ;
	    private ProcessInstance pi;
	    
	    public BaseCamunda() {
	        //this.processEngine = ProcessEngines.getDefaultProcessEngine();
	    	 this.processEngine = MostUsefulProcessEngineConfiguration.mostUsefulProcessEngineConfiguration().buildProcessEngine();
	    }
	    
	    public void deploy(final String... processModelResources) throws Exception {
	    	
	    	//repServ = processEngine.getRepositoryService();
	    	File f = new File("C:\\Users\\girish.r\\workspace\\bpmn-model-test\\src\\main\\resource\\manualpayment.bpmn");
	    	FileInputStream ip = new FileInputStream(f);
	    	final DeploymentBuilder deploymentBuilder=processEngine.getRepositoryService().createDeployment();
	    	for (final String resource : processModelResources) {
	            deploymentBuilder.addInputStream(resource, new FileInputStream(f));
	        }
	       this.deploymentIds.add(deploymentBuilder.name("manualpayment").deployWithResult().getId());
	    	/*DeploymentWithDefinitions dwd= deploymentBuilder.name("manualpayment").deployWithResult();
	    	List<ProcessDefinition> pdlist=dwd.getDeployedProcessDefinitions();
	    	for(ProcessDefinition prdef : pdlist) {
	    		System.out.println(prdef.getKey());
	    	}*/
	        getStartTime();
	    
	    	
	    	//final DeploymentWithDefinitions d = repServ.createDeployment().addInputStream("manualpayment.bpmn", new FileInputStream(f)).name("manualpayment.bpmn").deployWithResult();
	    	//pi=processEngine.getRuntimeService().startProcessInstanceByKey("manualpayment");	    		    	
	    }
	    
	    public void undeploy() {
	        for (final String deploymentId : deploymentIds) {
	            processEngine.getRepositoryService().deleteDeployment(deploymentId, true);
	        }
	        Mocks.reset();
	    }
	    
	    
	    public Date getStartTime() {
	        if (this.startTime == null) {
	            this.startTime = new Date();
	        }
	        return this.startTime;
	    }
	    
	    
	    public ProcessInstance startProcessInstanceByKey(final String processDefinitionKey, final Map<String, Object> variables) {
	        checkArgument(processDefinitionKey != null, "processDefinitionKey must not be null!");

	        final FluentProcessInstance instance = FluentProcessEngineTests.newProcessInstance(processDefinitionKey);
	        if (variables != null) {
	            instance.setVariables(variables);
	        }
	        return instance.start().getDelegate();
	    }
	    
	    public ProcessInstance startProcessInstanceByKey(final String processDefinitionKey) {
	        return startProcessInstanceByKey(processDefinitionKey, null);
	    }
	    
	    
	    public ProcessInstance getProcessInstance() {
	        return FluentProcessEngineTests.processInstance();
	    }
	    
	    public void setCurrentTime(final Date currentTime) {
	        ClockUtil.setCurrentTime(currentTime);
	    }
	    
	    public void resetClock() {
	        ClockUtil.reset();
	    }
	    
	    public static BaseCamunda getInstance() {
	        if (instance == null) {
	            instance = new BaseCamunda();
	            logger.debug("Camunda Support created.");
	        }
	        return instance;
	    }
	    
	    public ProcessEngine getProcessEngine() {
	        return processEngine;
	    }
	    
	    public Map<String, Object> getProcessVariables() {
	        return getProcessVariables();
	    }
	    
	    public void assertActivityVisitedOnce(final String id) {
	        final List<HistoricActivityInstance> visits = getProcessEngine().getHistoryService().createHistoricActivityInstanceQuery().finished()
	                .activityId(id).list();
	        assertThat("Expected element '" + id + "' not found!", visits, notNullValue());
	        assertThat("Expected element '" + id + "' not found!", visits, not(IsEmptyCollection.empty()));
	    }
	    
	    public void completeTask(final Object... values) {
	        FluentProcessEngineTests.processInstance().task().complete(values);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	        /*final DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
	        for (final String resource : processModelResources) {
	            deploymentBuilder.addClasspathResource(resource);
	        }
	        this.deploymentIds.add(deploymentBuilder.deploy().getId());
	        getStartTime();
	    */

}
