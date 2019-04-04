package com.hcl.retail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.BoughtProducts;
import com.hcl.retail.responsemodel.BuyResponse;
import com.hcl.retail.service.BuyService;
import com.hcl.retail.entity.CustomerRegistration;
@CrossOrigin
@RestController
public class BuyController {
	@Autowired
	BuyService buyService;
	private static final Logger logger = LoggerFactory.getLogger(BuyController.class);

	@PostMapping(value = "/buy")
	public BuyResponse buyCheck(@RequestBody BoughtProducts boughtProducts) {
		BoughtProducts boughtProductsDetails = buyService.buyCheck(boughtProducts.getUserId(),
				boughtProducts.getProductId(), boughtProducts.getProductDeliveryAddress());
		List<CustomerRegistration> customers = buyService.userCheck(boughtProducts.getUserId());
		logger.info("Entering into BuyController ==========>> ");
		int userId = 0;
		for (CustomerRegistration customer : customers) {
			userId = customer.getUserId();
		}
		if (userId != 0 && boughtProductsDetails.getProductName() != null) {
			BuyResponse buyResponse = new BuyResponse();
			buyResponse.setBoughtProductsDetails(boughtProductsDetails);
			buyResponse.setMessage("You have sucessfully purchased this product...Thank you....please visit again");
			logger.debug("BOUGHT THE PRODUCT ");
			buyResponse.setStatusCode("200");
			buyResponse.setStatusMessage("OK");
			return buyResponse;
		} else {
			BuyResponse buyResponse = new BuyResponse();
			buyResponse.setMessage("Something went wrong.....please try again");
			logger.warn("SOMETHING WENT WRONG ");
			buyResponse.setStatusCode("200");
			buyResponse.setStatusMessage("OK");
			return buyResponse;

		}
	}
}