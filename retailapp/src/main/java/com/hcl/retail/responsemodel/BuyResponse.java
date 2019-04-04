package com.hcl.retail.responsemodel;

import com.hcl.retail.entity.BoughtProducts;

public class BuyResponse {

	private BoughtProducts boughtProductsDetails;

	private String message;

	private String statusMessage;

	private String StatusCode;

	public BoughtProducts getBoughtProductsDetails() {
		return boughtProductsDetails;
	}

	public void setBoughtProductsDetails(BoughtProducts boughtProductsDetails) {
		this.boughtProductsDetails = boughtProductsDetails;
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
