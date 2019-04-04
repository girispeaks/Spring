package com.hcl.retail.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.retail.entity.CustomerRegistration;

@Repository
public interface CustomerRegistrationRepository extends CrudRepository<CustomerRegistration, String> {

	public List<CustomerRegistration> findByuserName(String name);

	public List<CustomerRegistration> findByuserId(int userId);

}
