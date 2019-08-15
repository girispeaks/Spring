package com.test.bpmn.JbpmJUnit;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.Assert.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import java.util.concurrent.TimeUnit;

import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.drools.core.time.impl.PseudoClockScheduler;
import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SampleTestProcess extends JbpmJUnitBaseTestCase{
	
	private RuntimeManager runtimeManager;
	private RuntimeEngine runtimeEngine;
	private KieSession kieSession;
	
	public SampleTestProcess() {
		super(true,true);
	}
	
	@Before
	public void init() {
		System.setProperty("drools.clockType", "pseudo");
		runtimeManager = createRuntimeManager("sample-process.bpmn");
		runtimeEngine = getRuntimeEngine(ProcessInstanceIdContext.get());
		kieSession = runtimeEngine.getKieSession();
	}
	
	@After
	public void tearDown() {
		runtimeManager.disposeRuntimeEngine(runtimeEngine);
		runtimeManager.close();
	}
	
	@Test
	public void testTimerActivated() {
		
		ProcessInstance pInstance = kieSession.startProcess("sample-process");
		long pInstanceId = pInstance.getId();
		
		
		PseudoClockScheduler sessionClock = kieSession.getSessionClock();
		
		
		
		sessionClock.advanceTime(70, TimeUnit.SECONDS);
		
		assertNodeTriggered(pInstanceId, "Goodbye Process");
		assertProcessInstanceCompleted(pInstanceId);
	
	}
	
}
