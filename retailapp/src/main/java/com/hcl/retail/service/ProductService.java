package com.hcl.retail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.ProductDetails;
import com.hcl.retail.repository.ProductRepository;

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	@Autowired
	ProductRepository productRepository;

	public ProductDetails productCheck(ProductDetails products) {
		logger.info("YOU GOT ASSESS INTO PRODUCT SERVICE>>>>>>>>> ");
		ProductDetails items = productRepository.save(products);
		return items;
	}
}