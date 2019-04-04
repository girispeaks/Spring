package com.hcl.retail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.AddProductDetails;
import com.hcl.retail.repository.AddProductRepository;

@Service
public class GetProductService
{
	@Autowired
	AddProductRepository productRepository;

	public List<AddProductDetails> productCheck(int categoryId)
	{
		List<AddProductDetails> productdetails =  productRepository.findBycategoryId(categoryId);
		return productdetails;
	}
}