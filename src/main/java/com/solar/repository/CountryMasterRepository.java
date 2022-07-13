package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.CountryMaster;
import com.solar.model.SubCompanyMaster;

@Repository
@Transactional
public interface CountryMasterRepository extends JpaRepository<CountryMaster, Integer>{

}
