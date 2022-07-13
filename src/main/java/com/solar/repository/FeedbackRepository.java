package com.solar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.Feedbacks;

@Repository
@Transactional
public interface FeedbackRepository extends JpaRepository<Feedbacks, Integer>{
	
//	Feedbacks FindById(int id);
	List<Feedbacks> findAllByOrderByIdDesc();
	
	@Query(value = "SELECT * FROM feedbacks WHERE ( date_of_create Like %?1% OR date_of_create Like %?2% OR date_of_create Like %?3% ) AND companyid =?4", nativeQuery = true)
	List<Feedbacks> findBYDateOfCreateAndCompanyid(@Param ("date1") String date1,@Param ("date2") String date2,@Param ("date3") String date3,@Param ("companyID") String companyID);

	List<Feedbacks> findByDateOfCreateBetweenOrderByIdDesc(Date FromDate, Date ToDate);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE feedbacks SET date_of_create=now() WHERE id=:id",nativeQuery = true)
	void updateCreateDate(@Param(value = "id") Integer id);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE feedbacks SET date_of_level1=now() WHERE id=:id",nativeQuery = true)
	void updateLevenOneDate(@Param(value = "id") Integer id);
}
