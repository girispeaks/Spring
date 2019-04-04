package com.hcl.retail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.AddProductDetails;
import com.hcl.retail.responsemodel.CustomerRegistrationResponse;
import com.hcl.retail.responsemodel.GetProductResponse;
import com.hcl.retail.service.GetProductService;

@RestController
public class GetProductController {
	@Autowired
	GetProductService productService;
	@PostMapping(value = "/productlist", produces = "application/json")
	public GetProductResponse productCheck(@RequestParam int categoryId) {
		List<AddProductDetails> productDetails = productService.productCheck(categoryId);
		if(productDetails!=null){
			GetProductResponse productResponse = new GetProductResponse();
			productResponse.setProductDetails(productDetails);
			productResponse.setMessage("your product details are above...");
			productResponse.setStatusCode("200");
			productResponse.setStatusMessage("OK");
			return productResponse;
		}
		else{
			GetProductResponse productResponse = new GetProductResponse();
			productResponse.setProductDetails(productDetails);
			productResponse.setMessage("can't get products...please try again");
			productResponse.setStatusCode("595");
			productResponse.setStatusMessage("NOT OK");
			return productResponse;
		}
}
}
