package com.hcl.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.retail.entity.AddCart;
import com.hcl.retail.entity.AddProductDetails;

@Repository
public interface AddCartRepository extends JpaRepository<AddCart, String>{
	public List<AddCart> findByuserId(int userId);
}