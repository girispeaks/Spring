package com.hcl.retail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.CategoryDetails;
import com.hcl.retail.repository.CategoryRepository;

@Service
public class CategoryService {
	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
	@Autowired
	CategoryRepository categoryRepository;

	public CategoryDetails categoryCheck(CategoryDetails categories) {
		logger.info("YOU GOT ASSESS INTO CATEGORYSERVICE>>>>>>>>> ");
		CategoryDetails items = categoryRepository.save(categories);
		return items;
	}
}