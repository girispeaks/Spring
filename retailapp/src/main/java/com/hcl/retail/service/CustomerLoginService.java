package com.hcl.retail.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.CategoryDetails;
import com.hcl.retail.entity.CustomerRegistration;
import com.hcl.retail.repository.CategoryRepository;
import com.hcl.retail.repository.CustomerRegistrationRepository;

@Service
public class CustomerLoginService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerLoginService.class);

	@Autowired
	CustomerRegistrationRepository loginRepository;
	@Autowired
	CategoryRepository categoryRepository;

	public List<CustomerRegistration> loginCheck(CustomerRegistration login) {
		logger.info("YOU GOT ASSESS INTO CUSTOMERLOGIN SERVICE>>>>>>>>> ");
		List<CustomerRegistration> users = loginRepository.findByuserName(login.getUserName());
		return users;
	}

	public List<CategoryDetails> getAll() {
		return categoryRepository.findAll();

	}

}