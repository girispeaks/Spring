package com.hcl.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.AddProductDetails;
import com.hcl.retail.service.AddProductService;

@RestController
@RequestMapping(value = "/admin")
public class AddProductController {
	@Autowired
	AddProductService productService;

	@PostMapping(value = "/products", produces = "application/json")
	public String productCheck(@RequestBody AddProductDetails productDetails) {
		AddProductDetails productdetail = productService.productCheck(productDetails);
		if (productdetail != null) {
			return "product added sucessfully";
		} else {
			return "category doesnt added";
		}
	}
}