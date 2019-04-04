package com.hcl.retail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.WishList;
import com.hcl.retail.responsemodel.MyWishListResponse;
import com.hcl.retail.service.MyWishListService;

@CrossOrigin
@RestController
public class MyWishListController {
	private static final Logger logger = LoggerFactory.getLogger(MyWishListController.class);
	@Autowired
	MyWishListService listService;

	@PostMapping(value = "/mywishlist")
	public MyWishListResponse myListCheck(@RequestParam int userId) {
		logger.info("THIS IS MYWISHLIST CONTROLLER......");
		List<WishList> wishListDetails = listService.myListCheck(userId);
		String description=null;
		for(WishList details:wishListDetails){
			description=details.getProductDescription();
		}
		if (description != null) {
			MyWishListResponse listResponse = new MyWishListResponse();
			listResponse.setWishListDetails(wishListDetails);
			listResponse.setMessage("Your wishlist is shown above");
			logger.debug("YOUR WISHLIST IS SHOWN HERE........");
			listResponse.setStatusCode("200");
			listResponse.setStatusMessage("OK");
			return listResponse;
		} else {
			MyWishListResponse listResponse = new MyWishListResponse();
			listResponse.setMessage("No products in your wishlist....");
			logger.warn("SEEMS YOU DIDN'T ADDED ANY PRODUCTS TO WISHLIST");
			listResponse.setStatusCode("200");
			listResponse.setStatusMessage("OK");
			return listResponse;

		}
	}
}