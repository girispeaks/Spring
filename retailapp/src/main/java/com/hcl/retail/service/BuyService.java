package com.hcl.retail.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.retail.entity.BoughtProducts;
import com.hcl.retail.entity.CustomerRegistration;
import com.hcl.retail.entity.ProductDetails;
import com.hcl.retail.repository.BuyRepository;
import com.hcl.retail.repository.CustomerRegistrationRepository;
import com.hcl.retail.repository.ProductRepository;

@Service
public class BuyService {

	@Autowired
	CustomerRegistrationRepository loginRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	BuyRepository buyRepository;

	private static final Logger logger = LoggerFactory.getLogger(BuyService.class);

	public BoughtProducts buyCheck(int userId, int productId, String productDeliveryAddress) {
		logger.debug("YOU GOT ASSESS INTO BUYSERVICE>>>>>>>>> ");

		String productname = null;
		String image = null;
		String description = null;

		List<ProductDetails> productdetails = productRepository.findByproductId(productId);
		for (ProductDetails details : productdetails) {

			productname = details.getProductName();
			image = details.getProductImage();
			description = details.getProductDescription();
		}
		BoughtProducts ownedproducts = new BoughtProducts();
		ownedproducts.setUserId(userId);
		ownedproducts.setProductId(productId);
		ownedproducts.setProductName(productname);
		ownedproducts.setProductImage(image);
		ownedproducts.setProductDescription(description);
		ownedproducts.setProductDeliveryAddress(productDeliveryAddress);

		BoughtProducts ownedGoods = buyRepository.save(ownedproducts);
		return ownedGoods;
	}

	public List<CustomerRegistration> userCheck(int userId) {
		List<CustomerRegistration> users = loginRepository.findByuserId(userId);
		return users;
	}
}