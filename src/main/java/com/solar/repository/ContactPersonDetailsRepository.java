package com.solar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solar.model.ContactPersonDetails;

@Repository
@Transactional
public interface ContactPersonDetailsRepository extends CrudRepository<ContactPersonDetails, Integer>{ 

	@Query(value="Select * from contact_person_details where custome_details_id = ?1", nativeQuery = true)
	ContactPersonDetails contactPersonDetailsData(int custId);
}
