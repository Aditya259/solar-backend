package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.Departments;

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Departments, Integer>{

	Departments findByName(String name);
	
	@Modifying
	@Query(value ="UPDATE departments SET create_at=now() WHERE id=:id",nativeQuery = true)
	void updateDate(@Param(value = "id") Integer id);
	
	
	
}
