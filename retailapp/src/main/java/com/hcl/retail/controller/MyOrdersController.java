package com.hcl.retail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.BoughtProducts;
import com.hcl.retail.responsemodel.MyOrdersResponse;
import com.hcl.retail.service.MyOrdersService;

@CrossOrigin
@RestController
public class MyOrdersController {
	private static final Logger logger = LoggerFactory.getLogger(MyOrdersController.class);
	@Autowired
	MyOrdersService orderService;

	@PostMapping(value = "/myorders")
	public MyOrdersResponse myOrdersCheck(@RequestParam int userId) {
		List<BoughtProducts> orderDetails = orderService.orderCheck(userId);
		int productId = 0;
		for (BoughtProducts orders : orderDetails) {
			productId = orders.getProductId();
		}

		if (productId != 0) {
			MyOrdersResponse listResponse = new MyOrdersResponse();
			listResponse.setOrderDetails(orderDetails);
			listResponse.setMessage("Your orders are shown above");
			logger.debug("YOUR ORDER DETAILS ARE HERE.......");
			listResponse.setStatusCode("200");
			listResponse.setStatusMessage("OK");
			return listResponse;
		} else {
			MyOrdersResponse listResponse = new MyOrdersResponse();
			listResponse.setMessage("seems you didn't placed any orders.....");
			logger.warn("SEEMS YOU DIDN'T PLACED ANY ORDERS.....");
			listResponse.setStatusCode("200");
			listResponse.setStatusMessage("OK");
			return listResponse;

		}
	}
}