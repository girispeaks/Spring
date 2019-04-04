package com.hcl.retail.responsemodel;

import java.util.List;

import com.hcl.retail.entity.CategoryDetails;

public class WelcomeResponse {
	List<CategoryDetails> categoryDetails;

	private String message;

	private String statusMessage;

	private String StatusCode;

	public List<CategoryDetails> getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(List<CategoryDetails> categoryDetails) {
		this.categoryDetails = categoryDetails;
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
