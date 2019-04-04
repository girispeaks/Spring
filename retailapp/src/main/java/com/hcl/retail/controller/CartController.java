package com.hcl.retail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.CartDetails;
import com.hcl.retail.responsemodel.CartResponse;
import com.hcl.retail.service.CartService;
@CrossOrigin
@RestController
public class CartController {
	@Autowired
	CartService cartService;
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@PostMapping(value = "/cart/add")
	public CartResponse addCart(@RequestBody CartDetails addcart) {
		logger.info("Entering into CartController ==========>> ");
		CartDetails cartdetails = cartService.addCart(addcart.getUserId(), addcart.getProductId(),
				addcart.getProductDeliveryTime());
		if (cartdetails.getProductDescription() != null) {
			CartResponse cartResponse = new CartResponse();
			cartResponse.setMessage("Your product is sucessfully added to cart");
			logger.debug(" Product added successfully");
			cartResponse.setStatusCode("200");
			cartResponse.setStatusMessage("OK");
			return cartResponse;
		} else {
			CartResponse cartResponse = new CartResponse();
			cartResponse.setMessage("Product is not added to cart");
			logger.warn(" Product added successfully");
			cartResponse.setStatusCode("200");
			cartResponse.setStatusMessage("OK");
			return cartResponse;

		}
	}
}