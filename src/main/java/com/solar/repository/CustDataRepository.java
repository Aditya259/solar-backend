package com.solar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.Complaints;
import com.solar.model.CustData;

@Repository
@Transactional
public interface CustDataRepository extends JpaRepository<CustData, Integer>{
	
	CustData findByid(Integer id);
	
	CustData findBycustomerCode(String id);
	
	CustData findByenquiryId(String id);
	
	List<CustData> findBycustomerName(String name);

}
