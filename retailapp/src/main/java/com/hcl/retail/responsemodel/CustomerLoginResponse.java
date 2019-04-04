package com.hcl.retail.responsemodel;

import java.util.List;

import com.hcl.retail.entity.CustomerRegistration;

public class CustomerLoginResponse {

	List<CustomerRegistration> customerregistration;

	private String message;

	private String statusMessage;

	private String StatusCode;

	public List<CustomerRegistration> getCustomerregistration() {
		return customerregistration;
	}

	public void setCustomerregistration(List<CustomerRegistration> customerregistration) {
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
