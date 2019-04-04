package com.hcl.retail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.service.CustomerRegistrationService;
import com.hcl.retail.entity.CustomerRegistration;
import com.hcl.retail.responsemodel.CustomerRegistrationResponse;

@CrossOrigin
@RestController
public class CustomerRegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationController.class);
	@Autowired
	CustomerRegistrationService registerService;

	@PostMapping(value = "/customer/register")
	public CustomerRegistrationResponse registerCheck(@RequestBody CustomerRegistration customerregistration) {
		logger.info("YOU ARE IN REGISTRATION CONTROLLER....");
		CustomerRegistration registerdetails = registerService.registerCheck(customerregistration);
		if (registerdetails.getUserName() != null) {
			CustomerRegistrationResponse registerResponse = new CustomerRegistrationResponse();
			registerResponse.setCustomerregistration(registerdetails);
			registerResponse.setMessage("Registration Success");
			logger.debug("REGISTRATION SUCCESS");
			registerResponse.setStatusCode("200");
			registerResponse.setStatusMessage("OK");
			return registerResponse;
		} else {
			CustomerRegistrationResponse registerResponse = new CustomerRegistrationResponse();
			registerResponse.setCustomerregistration(registerdetails);
			registerResponse.setMessage("Registration Failed");
			logger.warn("REGISTRATION FAILED");
			registerResponse.setStatusCode("200");
			registerResponse.setStatusMessage("OK");
			return registerResponse;

		}
	}
}
