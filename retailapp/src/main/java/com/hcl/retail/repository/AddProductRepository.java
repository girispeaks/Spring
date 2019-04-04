package com.hcl.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.retail.entity.AddProductDetails;


@Repository
public interface AddProductRepository extends JpaRepository<AddProductDetails, String>{
	public List<AddProductDetails> findBycategoryId(int categoryId);
	public List<AddProductDetails> findByproductId(int productId);
}