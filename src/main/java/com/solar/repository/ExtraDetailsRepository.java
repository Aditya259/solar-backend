package com.solar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solar.model.ExtraDetails;
@Repository
@Transactional
public interface ExtraDetailsRepository extends CrudRepository<ExtraDetails, Integer> {
	
	@Query(value="Select u from ExtraDetails u where u.customeDetailsId = :custId")
	ExtraDetails extraDetailsData(@Param("custId") int custId);

}
