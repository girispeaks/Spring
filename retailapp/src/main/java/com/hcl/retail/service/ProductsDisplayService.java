package com.hcl.retail.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.ProductDetails;
import com.hcl.retail.repository.ProductRepository;

@Service
public class ProductsDisplayService {
	private static final Logger logger = LoggerFactory.getLogger(ProductsDisplayService.class);
	@Autowired
	ProductRepository productRepository;

	public List<ProductDetails> productCheck(int categoryId) {
		logger.info("YOU GOT ASSESS INTO PRODUCTS DISPLAY SERVICE>>>>>>>>> ");
		List<ProductDetails> productdisplaydetails = productRepository.findBycategoryId(categoryId);

		return productdisplaydetails;
	}
}