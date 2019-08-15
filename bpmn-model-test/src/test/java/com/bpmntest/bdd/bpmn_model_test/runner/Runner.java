package com.bpmntest.bdd.bpmn_model_test.runner;

import org.junit.runner.RunWith;

//import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resource\\features", glue="com.bpmntest.bdd.steps", plugin={"pretty"})
public class Runner {

}
