package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.SubChannelMaster;
import com.solar.model.SubCompanyMaster;

@Repository
@Transactional
public interface SubCompanyMasterRepository extends JpaRepository<SubCompanyMaster, Integer>{

}
