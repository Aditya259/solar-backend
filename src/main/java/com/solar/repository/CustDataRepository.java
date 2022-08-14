package com.solar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.CustData;

@Repository
@Transactional
public interface CustDataRepository extends JpaRepository<CustData, Integer>{
	
	CustData findByid(Integer id);
	
	CustData findBycustomerCode(String id);
	
	CustData findByenquiryId(String id);
	
	List<CustData> findBycustomerName(String name);
	
	@Query("select c from CustData c where c.company = :#{#custData.company} AND c.customerCode = :#{#custData.customerCode} AND"
			+ "c.customerName = :#{#custData.customerName} AND c.extLogDate >= :#{#custData.extLogDate} AND"
			+ "c.extPurposeOfEmail = :#{#custData.extPurposeOfEmail} AND c.subsidaryCompany = :#{#custData.subsidaryCompany} AND "
			+ "c.extLogDate <= :#{#custData.extLogDate} AND c.sector = :#{#custData.sector} AND "
			+ "c.enquiryId = :#{#custData.enquiryId} AND c.employeeName = :#{#custData.employeeName}")
	List<CustData> findFilterData(@Param("custData") CustData custData);
	
	@Query("select c from CustData c where c.enquiryId = :enquiryId AND c.customerName = :customerName AND c.customerCode = :customerCode AND"
			+ "c.sector = :sector AND c.subsidaryCompany = :subsidaryCompany AND c.company = :company AND c.country = :country AND"
			+ "c.state = :state")
	List<CustData> filterCustomData(@Param("custData") CustData custData);
	
	
	
}
