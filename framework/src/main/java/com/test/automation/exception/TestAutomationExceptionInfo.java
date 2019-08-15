package com.test.automation.exception;

public class TestAutomationExceptionInfo {

	private String errorCode;
	
	private String errorDescription;
	
	public static final TestAutomationExceptionInfo BCS0000 = new TestAutomationExceptionInfo("BCS0000", "Failed to instantiate the BCS connection");
	
	public static final TestAutomationExceptionInfo BCS0001 = new TestAutomationExceptionInfo("BCS0001", "Matching records not found in BCS");
	
	public static final TestAutomationExceptionInfo BCS0002 = new TestAutomationExceptionInfo("BCS0002", "Failed to close the BCS connection");
	
	public static final TestAutomationExceptionInfo BCS0003 = new TestAutomationExceptionInfo("BCS0003", "BCS response results in Error");
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorDescription() {
		return errorDescription;
	}
	
	public TestAutomationExceptionInfo(String errorCode, String errorDescription) {
		this.errorCode=errorCode;
		this.errorDescription=errorDescription;
	}
	
}
