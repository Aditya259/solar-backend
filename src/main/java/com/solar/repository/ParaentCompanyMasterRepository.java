package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.ParaentCompanyMaster;
import com.solar.model.StateMaster;

@Repository
@Transactional
public interface ParaentCompanyMasterRepository extends JpaRepository<ParaentCompanyMaster, Integer>{

}
