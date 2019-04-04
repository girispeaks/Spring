package com.hcl.retail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.CartDetails;
import com.hcl.retail.responsemodel.MyCartResponse;
import com.hcl.retail.service.MyCartService;

@CrossOrigin
@RestController
public class MyCartController {
	private static final Logger logger = LoggerFactory.getLogger(MyCartController.class);
	@Autowired
	MyCartService cartService;

	@PostMapping(value = "/mycart")
	public MyCartResponse addCheck(@RequestParam int userId) {
		logger.info("YOU ARE IN MY CART CONTROLLER");
		List<CartDetails> cartDetails = cartService.cartCheck(userId);
		String productName = null;
		for (CartDetails details : cartDetails) {
			productName = details.getProductName();
		}
		if (productName != null) {
			MyCartResponse cartResponse = new MyCartResponse();
			cartResponse.setCartDetails(cartDetails);
			cartResponse.setMessage("Your cart details are shown above");
			logger.debug("CART DETAILS ARE HERE");
			cartResponse.setStatusCode("200");
			cartResponse.setStatusMessage("OK");
			return cartResponse;
		} else {
			MyCartResponse cartResponse = new MyCartResponse();
			cartResponse.setCartDetails(cartDetails);
			cartResponse.setMessage("Failed to get your cart details....");
			logger.warn("THERE IS AN ERROR IN FETCHING YOUR CART");
			cartResponse.setStatusCode("200");
			cartResponse.setStatusMessage("OK");
			return cartResponse;

		}
	}
}