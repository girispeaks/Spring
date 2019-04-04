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
import com.hcl.retail.responsemodel.CustomerLoginResponse;
import com.hcl.retail.service.CustomerLoginService;

@CrossOrigin
@RestController
public class CustomerLoginController {
	@Autowired
	CustomerLoginService loginService;
	private static final Logger logger = LoggerFactory.getLogger(CustomerLoginController.class);

	@PostMapping(value = "/customer/login", produces = "application/json")
	public CustomerLoginResponse loginCheck(@RequestBody CustomerRegistration login) {
		List<CustomerRegistration> logindetails = loginService.loginCheck(login);
		String name = null;
		String password = null;
		for (CustomerRegistration customerlogin : logindetails) {
			name = customerlogin.getUserName();
			password = customerlogin.getUserPassword();
		}
		logger.debug("Entering into LoginController ==========>> ");
		if (login.getUserName().equals(name) && login.getUserPassword().equals(password)) {

			CustomerLoginResponse loginResponse = new CustomerLoginResponse();
			loginResponse.setCustomerregistration(logindetails);
		
			loginResponse.setMessage("Login Success.....Welcome");
			logger.debug("LOGIN SUCCESS");
			loginResponse.setStatusCode("200");
			loginResponse.setStatusMessage("OK");
			return loginResponse;
		} else {
			CustomerLoginResponse loginResponse = new CustomerLoginResponse();
			loginResponse.setMessage("Login Failure");
			loginResponse.setStatusCode("200");
			logger.warn("PLEASE ENTER CORRECT DETAILS....");
			loginResponse.setStatusMessage("OK");
			return loginResponse;
		}
	}

}
