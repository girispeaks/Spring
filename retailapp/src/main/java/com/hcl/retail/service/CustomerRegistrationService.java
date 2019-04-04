package com.hcl.retail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.CustomerRegistration;
import com.hcl.retail.repository.CustomerRegistrationRepository;

@Service
public class CustomerRegistrationService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationService.class);
	@Autowired
	CustomerRegistrationRepository registerRepository;

	public CustomerRegistration registerCheck(CustomerRegistration customerregistration) {
		logger.info("YOU GOT ASSESS INTO CUSTOMERREGISTRATION SERVICE>>>>>>>>> ");
		CustomerRegistration users = registerRepository.save(customerregistration);
		return users;
	}
}
