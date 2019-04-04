package com.hcl.retail.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.CartDetails;
import com.hcl.retail.entity.ProductDetails;
import com.hcl.retail.repository.CartRepository;
import com.hcl.retail.repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CartRepository cartRepository;

	private static final Logger logger = LoggerFactory.getLogger(BuyService.class);

	public CartDetails addCart(int userId, int productId, String productDeliveryTime) {
		logger.debug("YOU GOT ASSESS INTO CARTSERVICE>>>>>>>>> ");

		String productname = null;
		int price = 0;
		String description = null;

		List<ProductDetails> productdetails = productRepository.findByproductId(productId);
		for (ProductDetails details : productdetails) {

			productname = details.getProductName();
			price = details.getProductPrice();
			description = details.getProductDescription();
		}
		CartDetails addcartdetails = new CartDetails();
		addcartdetails.setUserId(userId);
		addcartdetails.setProductId(productId);
		addcartdetails.setProductName(productname);
		addcartdetails.setProductPrice(price);
		addcartdetails.setProductDescription(description);
		addcartdetails.setProductDeliveryTime(productDeliveryTime);

		CartDetails users = cartRepository.save(addcartdetails);
		return users;
	}
}