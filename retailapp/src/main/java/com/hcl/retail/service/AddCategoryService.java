package com.hcl.retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.AddCategoryDetails;
import com.hcl.retail.repository.AddCategoryRepository;

@Service
public class AddCategoryService
{
	@Autowired
	AddCategoryRepository categoryRepository;

	public AddCategoryDetails categoryCheck(AddCategoryDetails categories)
	{
		AddCategoryDetails items =  categoryRepository.save(categories);
		return items;
	}
}