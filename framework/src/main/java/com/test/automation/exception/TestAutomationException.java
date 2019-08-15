package com.test.automation.exception;

public class TestAutomationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_ERROR_CODE = "API000";
	
	private String errorCode;
	
	private String errorMessage;
	
	private String description;
	
	public TestAutomationException(TestAutomationExceptionInfo exceptionInfo) {
		this.errorCode = exceptionInfo.getErrorCode();
		this.errorMessage = exceptionInfo.getErrorDescription();
	}
	
	public TestAutomationException(String errorCode, String message) {
		super(message);
		this.errorMessage = message;
		this.errorCode = errorCode;
	}
	
	public TestAutomationException(String message) {
		super(message);
		this.description = message;
		this.errorCode = DEFAULT_ERROR_CODE;
	}
	
	public TestAutomationException(String message, Throwable cause) {
		super(message, cause);
		this.description = message;
		this.errorCode = DEFAULT_ERROR_CODE;
	}
	
	public TestAutomationException(Throwable cause) {
		super(cause);
		this.setDescription((cause == null) ? null : cause.toString());
		this.errorCode = DEFAULT_ERROR_CODE;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage=errorMessage;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
}
