package com.hcl.retail.responsemodel;

import java.util.List;

import com.hcl.retail.entity.AddCart;

public class AddCartResponse {
	
	private AddCart addcartdetails;

	private String message;

	private String statusMessage;

	private String StatusCode;

	public AddCart getAddcartdetails() {
		return addcartdetails;
	}

	public void setAddcartdetails(AddCart addcartdetails) {
		this.addcartdetails = addcartdetails;
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
