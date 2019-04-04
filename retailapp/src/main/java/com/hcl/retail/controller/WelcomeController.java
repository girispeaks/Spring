package com.hcl.retail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.CategoryDetails;
import com.hcl.retail.entity.CustomerRegistration;
import com.hcl.retail.responsemodel.WelcomeResponse;
import com.hcl.retail.service.CustomerLoginService;

@CrossOrigin
@RestController
public class WelcomeController {
	@Autowired
	CustomerLoginService loginService;
	private static final Logger logger = LoggerFactory.getLogger(CustomerLoginController.class);

	@PostMapping(value = "/welcome", produces = "application/json")
	public WelcomeResponse loginCheck() {
		List<CategoryDetails> categoryDetails = loginService.getAll();
		String image = null;
		for (CategoryDetails category : categoryDetails) {
			image = category.getCategoryImage();

		}
		if (image != null) {

			WelcomeResponse welcomeResponse = new WelcomeResponse();

			welcomeResponse.setCategoryDetails(categoryDetails);
			welcomeResponse.setMessage("Available categories for you....");

			welcomeResponse.setStatusCode("200");
			welcomeResponse.setStatusMessage("OK");
			return welcomeResponse;
		} else {
			WelcomeResponse welcomeResponse = new WelcomeResponse();
			welcomeResponse.setMessage("can't find categories for you");
			welcomeResponse.setStatusCode("200");
			logger.warn("PLEASE ENTER CORRECT DETAILS....");
			welcomeResponse.setStatusMessage("OK");
			return welcomeResponse;
		}
	}

}
