package com.solar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solar.model.ContactPersonDetails;
import com.solar.model.ExtraDetails;
@Repository
@Transactional
public interface ExtraDetailsRepository extends CrudRepository<ExtraDetails, Integer> {
	
	@Query(value="Select * from extra_details where custome_details_id = ?1", nativeQuery = true)
	ExtraDetails extraDetailsData(int custId);

}
