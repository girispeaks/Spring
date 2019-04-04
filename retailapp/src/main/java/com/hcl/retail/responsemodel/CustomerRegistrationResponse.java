package com.hcl.retail.responsemodel;

import com.hcl.retail.entity.CustomerRegistration;

public class CustomerRegistrationResponse {

	private CustomerRegistration customerregistration;

	private String message;

	private String statusMessage;

	private String StatusCode;

	public CustomerRegistration getCustomerregistration() {
		return customerregistration;
	}

	public void setCustomerregistration(CustomerRegistration customerregistration) {
		this.customerregistration = customerregistration;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}

}
