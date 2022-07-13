package com.solar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.Complaints;

@Repository
@Transactional
public interface ComplaintRepository extends JpaRepository<Complaints, Integer>{
		
	List<Complaints> findByApprovalStageDepartmentOrderByIdDesc(String department);
	List<Complaints> findAllByOrderByIdDesc();
	
	List<Complaints> findByCompanyIdAndDateOfCreateBetweenOrderByIdDesc(String companyId, Date FromDate, Date ToDate);
	
	List<Complaints> findByDateOfCreateBetweenOrderByIdDesc(Date FromDate, Date ToDate);
	
	List<Complaints> findByapprovalStageSellPersonIdOrderByIdDesc(Integer approvalStageSellPersonID);

	@Modifying
	@Transactional
	@Query(value ="UPDATE complaints SET date_of_create=now() WHERE id=:id",nativeQuery = true)
	Complaints updateCreateDate(@Param(value = "id") Integer id);
	
	@Modifying@Transactional
	@Query(value ="UPDATE complaints SET date_of_level1=now() WHERE id=:id",nativeQuery = true)
	void updateLevenOneDate(@Param(value = "id") Integer id);
	
	@Modifying@Transactional
	@Query(value ="UPDATE complaints SET date_of_level2=now() WHERE id=:id",nativeQuery = true)
	void updateLevenTwoDate(@Param(value = "id") Integer id);
	
	@Modifying@Transactional
	@Query(value ="UPDATE complaints SET date_of_level3=now() WHERE id=:id",nativeQuery = true)
	void updateLevenThreeDate(@Param(value = "id") Integer id);
	
	@Modifying@Transactional
	@Query(value ="UPDATE complaints SET date_of_level4=now() WHERE id=:id",nativeQuery = true)
	void updateLevenFourDate(@Param(value = "id") Integer id);
	
	@Modifying@Transactional
	@Query(value ="UPDATE complaints SET date_of_closed=now() WHERE id=:id",nativeQuery = true)
	void updateLevenCloseDate(@Param(value = "id") Integer id);
}
