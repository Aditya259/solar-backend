package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.CountryMaster;
import com.solar.model.StateMaster;

@Repository
@Transactional
public interface StateMasterRepository extends JpaRepository<StateMaster, Integer>{

}
