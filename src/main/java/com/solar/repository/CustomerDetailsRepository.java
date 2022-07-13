package com.solar.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solar.model.CustomerDetails;
import com.solar.model.FilterData;


@Repository
@Transactional
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Integer>{


	@Query(value="Select * from customer_details where customerName LIKE ?1% AND existingFlag = ?2", nativeQuery = true)
	List<CustomerDetails> customerNameList(String existingCustomerName,String existingFlag);
	
	@Query(value="Select * from customer_details where customerName = ?1", nativeQuery = true)
	CustomerDetails customerNameData(String existingCustomerName);
	
	/**
	@Query(value="SELECT cd.customerName,cd.subsidaryCompany,cd.company,ed.purpose_of_email\r\n"
			+ "FROM customer_details cd\r\n"
			+ "LEFT JOIN contact_person_details cpd ON cd.id = cpd.custome_details_id\r\n"
			+ "LEFT JOIN extra_details ed ON  cd.id = ed.custome_details_id\r\n"
			+ "WHERE cd.customerName = 'Aditya' AND cd.subsidaryCompany='NISAN'\r\n"
			+ "AND cd.company = 'TechM' \r\n"
			+ "AND ed.purpose_of_email='Finance'", nativeQuery = true)
	Map<String,String> fetchFilteredData();
	**/
	
	@Query(value="SELECT cd.customerName,cd.subsidaryCompany,cd.company,ed.purpose_of_email as purpose\r\n"
			+ "FROM customer_details cd\r\n"
			+ "LEFT JOIN contact_person_details cpd ON cd.id = cpd.custome_details_id\r\n"
			+ "LEFT JOIN extra_details ed ON  cd.id = ed.custome_details_id", nativeQuery = true)
	Map<String,String> fetchFilteredData();
	
}
