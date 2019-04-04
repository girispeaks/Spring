package com.hcl.retail.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.ProductDetails;
import com.hcl.retail.responsemodel.ProductDisplay;
import com.hcl.retail.responsemodel.ProductDisplayResponse;
import com.hcl.retail.service.ProductsDisplayService;

@CrossOrigin
@RestController
public class ProductsDisplayController {
	private static final Logger logger = LoggerFactory.getLogger(ProductsDisplayController.class);
	@Autowired
	ProductsDisplayService productService;

	@PostMapping(value = "/products/display", produces = "application/json")
	public ProductDisplayResponse productCheck(@RequestParam int categoryId) {
		logger.info("YOU ARE JUST IN PRODUCTS DISPLAY CONTROLLER");
		List<ProductDetails> products = productService.productCheck(categoryId);
		int Id = 0;
		for (ProductDetails details : products) {
			Id = details.getProductId();
		}
		if (Id != 0) {
			ProductDisplayResponse productDisplayResponse = new ProductDisplayResponse();
			List<ProductDisplay> display = new ArrayList<ProductDisplay>();
			for (ProductDetails productDetails : products) {
				ProductDisplay productDisplay = new ProductDisplay();
				productDisplay.setProductId(productDetails.getProductId());
				
				productDisplay.setProductName(productDetails.getProductName());
				productDisplay.setProductImage(productDetails.getProductImage());
				productDisplay.setProductPrice(productDetails.getProductPrice());
				display.add(productDisplay);
			}

			productDisplayResponse.setDisplay(display);
			productDisplayResponse.setMessage("Available products are shown above...");
			logger.debug("MOST AMAZING PRODUCTS FOR YOU>>>>>>");
			productDisplayResponse.setStatusCode("200");
			productDisplayResponse.setStatusMessage("OK");
			return productDisplayResponse;
		} else {
			ProductDisplayResponse productDisplayResponse = new ProductDisplayResponse();
			productDisplayResponse.setMessage("can't get products for you....please try again");
			logger.debug("THERE IS AN ERROR IN GETTING PRODUCTS");
			productDisplayResponse.setStatusCode("200");
			productDisplayResponse.setStatusMessage("OK");
			return productDisplayResponse;
		}
	}
}
