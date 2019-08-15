package com.bpmntest.bdd.bpmn_model_test.runner;

import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.ClassRule;
import org.junit.Rule;

@Deployment(resources="manualpayment.bpmn")
public class World {
	@Rule
	@ClassRule
	public ProcessEngineRule rule=new ProcessEngineRule(new StandaloneInMemProcessEngineConfiguration().buildProcessEngine());
	
	
}
