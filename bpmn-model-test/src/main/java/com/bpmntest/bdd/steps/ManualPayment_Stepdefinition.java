package com.bpmntest.bdd.steps;

import static org.junit.Assert.assertNotNull;
import org.camunda.bpm.engine.runtime.ProcessInstance;
//import org.camunda.bpm.engine.test.fluent.FluentProcessEngineTestRule;
import org.camunda.bpm.engine.test.fluent.FluentProcessEngineTests;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bpmntest.bdd.bpmn_model_test.BaseCamunda;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//@Deployment(resources="manualpayment.bpmn")
public class ManualPayment_Stepdefinition extends FluentProcessEngineTests{
	
	private static final Logger LOG = LoggerFactory.getLogger(ManualPayment_Stepdefinition.class);

	//@Inject
	private BaseCamunda support=new BaseCamunda();;

	@Before
	public void init() {
		LOG.debug("Initializing before a story run.");
		FluentProcessEngineTests.before(support.getProcessEngine());
	}

	@After
	public void cleanUp() {
		LOG.debug("Cleaning up after story run.");
		Mocks.reset();
		support.undeploy();
		support.resetClock();
		// FluentProcessEngineTests.after(); \"(.*)\"
	}

	@Given("^the process definition \"(.*)\"$")
	public void deployProcess(final String processDefinition) throws Exception {
		System.out.println("process Definition is "+processDefinition);
		support.deploy(processDefinition);
	}

	@When("^the process is started \"(.*)\"$")
	public void startProcess(final String processKey) {
		final ProcessInstance processInstance = support.startProcessInstanceByKey(processKey);
		assertNotNull("The process with the definitionKey '" + processKey + "' has not been started.", processInstance);
	}

	@And("^I verify the task is assigned to john$")
	public void i_verify_the_task_is_assigned_to_john() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}

	@Then("^I complete the task$")
	public void i_complete_the_task() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@And("^I verity the task is completed$")
	public void i_verity_the_task_is_completed() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	/*
	 * @Before public void beforeScenario() { }
	 * 
	 * public manualPayment_Stepdefinition(World world) { this.world=world; }
	 * 
	 * @Given("^I have the process instance ready$")
	 * //@Deployment(resources="manualpayment.bpmn") //@Test public void
	 * i_have_the_process_instance_ready() throws Throwable { setProcessInstance();
	 * //world.rule= TestCoverageProcessEngineRuleBuilder.create().build();
	 * this.rule=world.rule; processEngine=rule.getProcessEngine();
	 * pi=processEngine.getRuntimeService().startProcessInstanceByKey(
	 * "manualpayment"); }
	 * 
	 * @And("^I verify the task is assigned to john$") public void
	 * i_verify_the_task_is_assigned_to_john() throws Throwable { // Write code here
	 * that turns the phrase above into concrete actions
	 * 
	 * }
	 * 
	 * @Then("^I complete the task$") public void i_complete_the_task() throws
	 * Throwable { // Write code here that turns the phrase above into concrete
	 * actions
	 * 
	 * }
	 * 
	 * @And("^I verity the task is completed$") public void
	 * i_verity_the_task_is_completed() throws Throwable { // Write code here that
	 * turns the phrase above into concrete actions
	 * 
	 * }
	 * 
	 * @Deployment(resources="manualpayment.bpmn")
	 * 
	 * @Test public void setProcessInstance() {
	 * processEngine=rule.getProcessEngine(); }
	 */

}
