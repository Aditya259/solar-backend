package com.solar.repository;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.solar.model.CustomerDetails;

@Repository
@Transactional
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Integer> {

	@Query(value = "Select u from CustomerDetails u where u.customerName like :existingCustomerName AND u.existingFlag = :existingFlag")
	List<CustomerDetails> customerNameList(@Param("existingCustomerName") String existingCustomerName,
			@Param("existingFlag") String existingFlag);

	@Query(value = "Select u from CustomerDetails u  where u.customerName = :existingCustomerName")
	CustomerDetails customerNameData(@Param("existingCustomerName") String existingCustomerName);

	@Query(value = "SELECT cd.customerName,cd.subsidaryCompany,cd.company,ed.purpose_of_email as purpose\r\n"
			+ "FROM customer_details cd\r\n"
			+ "LEFT JOIN contact_person_details cpd ON cd.id = cpd.custome_details_id\r\n"
			+ "LEFT JOIN extra_details ed ON  cd.id = ed.custome_details_id", nativeQuery = true)
	Map<String, String> fetchFilteredData();

}
