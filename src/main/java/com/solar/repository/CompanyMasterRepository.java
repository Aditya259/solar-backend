package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.CompanyMaster;

@Repository
@Transactional
public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, Integer>{

}
