package com.hcl.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.AddCart;
import com.hcl.retail.responsemodel.AddCartResponse;
import com.hcl.retail.service.AddCartService;

@RestController
public class AddCartController {
	@Autowired
	AddCartService cartService;
	@PostMapping(value = "/addcart")
	public AddCartResponse addCheck(@RequestBody AddCart addcart) {
		AddCart addcartdetails = cartService.addCheck(addcart.getUserId(),addcart.getProductId(),addcart.getProductDeliveryTime());
		if (addcartdetails!=null) {
		AddCartResponse cartResponse = new AddCartResponse();
		cartResponse.setAddcartdetails(addcartdetails);
		cartResponse.setMessage("Your product is sucessfully added to cart");
		cartResponse.setStatusCode("200");
		cartResponse.setStatusMessage("OK");
		return cartResponse;
		}
		else{
			AddCartResponse cartResponse = new AddCartResponse();
			cartResponse.setAddcartdetails(addcartdetails);
			cartResponse.setMessage("Product is not added to cart");
			cartResponse.setStatusCode("595");
			cartResponse.setStatusMessage("NOT OK");
			return cartResponse;
			
		}
	}
}