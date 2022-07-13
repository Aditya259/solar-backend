package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.CompanyMaster;
import com.solar.model.EmployeeMaster;

@Repository
@Transactional
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Integer>{

}
