package com.hcl.retail.responsemodel;

import java.util.List;

import com.hcl.retail.entity.WishList;

public class MyWishListResponse {

	List<WishList> wishListDetails;

	private String message;

	private String statusMessage;

	private String StatusCode;

	public List<WishList> getWishListDetails() {
		return wishListDetails;
	}

	public void setWishListDetails(List<WishList> wishListDetails) {
		this.wishListDetails = wishListDetails;
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
