package com.hcl.retail.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.CartDetails;
import com.hcl.retail.repository.CartRepository;

@Service
public class MyCartService {
	private static final Logger logger = LoggerFactory.getLogger(MyCartService.class);
	@Autowired
	CartRepository cartRepository;

	public List<CartDetails> cartCheck(int userId) {
		logger.info("YOU GOT ASSESS INTO MYCART SERVICE>>>>>>>>> ");
		List<CartDetails> cartdetails = cartRepository.findByuserId(userId);
		return cartdetails;
	}
}