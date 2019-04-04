package com.hcl.retail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.ProductDetails;
import com.hcl.retail.responsemodel.ProductResponse;
import com.hcl.retail.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	ProductService productService;

	@PostMapping(value = "/products/add", produces = "application/json")
	public ProductResponse productCheck(@RequestBody ProductDetails productDetails) {
		logger.info("THIS IS PRODUCT CONTROLLER>>>>>>>>>>");
		ProductDetails productdetail = productService.productCheck(productDetails);
		if (productdetail.getProductName() != null) {
			ProductResponse productResponse = new ProductResponse();
			productResponse.setMessage("Product added successfully..");
			logger.debug("PRODUCT GOT ADDED SUCCESSFULLY");
			productResponse.setStatusCode("200");
			productResponse.setStatusMessage("OK");
			return productResponse;
		} else {
			ProductResponse productResponse = new ProductResponse();
			productResponse.setMessage("Product not added ....");
			logger.warn("PRODUCTS DIDN'T GOT ADDED");
			productResponse.setStatusCode("200");
			productResponse.setStatusMessage("OK");
			return productResponse;
		}
	}
}