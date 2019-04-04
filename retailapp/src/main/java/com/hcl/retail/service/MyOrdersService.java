package com.hcl.retail.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.BoughtProducts;
import com.hcl.retail.repository.BuyRepository;

@Service
public class MyOrdersService {
	private static final Logger logger = LoggerFactory.getLogger(MyOrdersService.class);
	@Autowired
	BuyRepository buyRepository;

	public List<BoughtProducts> orderCheck(int userId) {
		logger.info("YOU GOT ASSESS INTO MYORDERS SERVICE>>>>>>>>> ");
		List<BoughtProducts> listdetails = buyRepository.findByuserId(userId);
		return listdetails;
	}
}