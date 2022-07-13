package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.CompanyMaster;
import com.solar.model.IndustryMaster;

@Repository
@Transactional
public interface IndustryMasterRepository extends JpaRepository<IndustryMaster, Integer>{

}
