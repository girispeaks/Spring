package com.hcl.retail.responsemodel;

import com.hcl.retail.entity.CategoryDetails;

public class CategoryResponse {
	
	private CategoryDetails categoryDetail;
	
	private String message;

	private String statusMessage;

	private String StatusCode;

	public CategoryDetails getCategoryDetail() {
		return categoryDetail;
	}

	public void setCategoryDetail(CategoryDetails categoryDetail) {
		this.categoryDetail = categoryDetail;
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
