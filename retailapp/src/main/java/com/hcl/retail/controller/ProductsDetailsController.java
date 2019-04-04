package com.hcl.retail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.ProductDetails;
import com.hcl.retail.responsemodel.ProductDetailsResponse;
import com.hcl.retail.service.ProductsDetailsService;

@CrossOrigin
@RestController
public class ProductsDetailsController {
	private static final Logger logger = LoggerFactory.getLogger(ProductsDetailsController.class);
	@Autowired
	ProductsDetailsService productService;

	@PostMapping(value = "/products/details", produces = "application/json")
	public ProductDetailsResponse productCheck(@RequestParam int productId) {
		logger.info("YOU ARE ASSESSING PRODUCTSDETAILS CONTROLLER");
		List<ProductDetails> productDetails = productService.productCheck(productId);
		if (productDetails != null) {
			ProductDetailsResponse productResponse = new ProductDetailsResponse();
			productResponse.setProductDetails(productDetails);
			productResponse.setMessage("your product details are above...");
			logger.debug("YOUR PRODUCT DETAILS ARE HERE");
			productResponse.setStatusCode("200");
			productResponse.setStatusMessage("OK");
			return productResponse;
		} else {
			ProductDetailsResponse productResponse = new ProductDetailsResponse();
			productResponse.setMessage("can't get your products details...please try again");
			logger.warn("UNABLE TO GET YOUR PRODUCTS>>>>>>>");
			productResponse.setStatusCode("200");
			productResponse.setStatusMessage("OK");
			return productResponse;
		}
	}
}
