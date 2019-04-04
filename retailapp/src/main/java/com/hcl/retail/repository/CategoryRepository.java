package com.hcl.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.retail.entity.CategoryDetails;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDetails, String> {

}