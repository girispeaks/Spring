package com.hcl.retail.responsemodel;

import java.util.List;

public class ProductDisplayResponse {

	List<ProductDisplay> display;

	private String message;

	private String statusMessage;

	private String StatusCode;

	public List<ProductDisplay> getDisplay() {
		return display;
	}

	public void setDisplay(List<ProductDisplay> display) {
		this.display = display;
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
