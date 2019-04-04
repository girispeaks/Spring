package com.hcl.retail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.CustomerRegistration;
import com.hcl.retail.entity.WishList;
import com.hcl.retail.responsemodel.WishListResponse;
import com.hcl.retail.service.WishListService;

@CrossOrigin
@RestController
public class WishListController {

	private static final Logger logger = LoggerFactory.getLogger(WishListController.class);

	@Autowired
	WishListService wishListService;

	@PostMapping(value = "/wishlist/add")
	public WishListResponse wishListAdd(@RequestBody WishList wishlist) {
		logger.info("YOU ARE IN WISHLIST CONTROLLER");
		WishList wishlistdetails = wishListService.wishListAdd(wishlist.getUserId(), wishlist.getProductId(),
				wishlist.getProductDeliveryTime());
		List<CustomerRegistration> customers = wishListService.userCheck(wishlist.getUserId());
		logger.info("Entering into BuyController ==========>> ");
		int userId = 0;
		for (CustomerRegistration customer : customers) {
			userId = customer.getUserId();
		}
		if (userId != 0 && wishlistdetails.getProductName() != null) {
			WishListResponse listResponse = new WishListResponse();
			listResponse.setMessage(" product is moved to your wishlist");
			logger.debug("THIS PRODUCT IS MOVED TO WISHLIST");
			listResponse.setStatusCode("200");
			listResponse.setStatusMessage("OK");
			return listResponse;
		} else {
			WishListResponse listResponse = new WishListResponse();
			listResponse.setMessage("cant't add product to wishlist");
			logger.warn("CAN'T ADD PRODUCTS TO WISHLIST");
			listResponse.setStatusCode("200");
			listResponse.setStatusMessage("OK");
			return listResponse;

		}
	}
}