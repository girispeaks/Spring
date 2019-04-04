package com.hcl.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.retail.entity.ProductDetails;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails, String> {
	public List<ProductDetails> findBycategoryId(int categoryId);

	public List<ProductDetails> findByproductId(int productId);
}