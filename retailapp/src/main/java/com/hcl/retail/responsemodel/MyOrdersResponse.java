package com.hcl.retail.responsemodel;

import java.util.List;

import com.hcl.retail.entity.BoughtProducts;

public class MyOrdersResponse {

	List<BoughtProducts> orderDetails;

	private String message;

	private String statusMessage;

	private String StatusCode;

	public List<BoughtProducts> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<BoughtProducts> orderDetails) {
		this.orderDetails = orderDetails;
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
