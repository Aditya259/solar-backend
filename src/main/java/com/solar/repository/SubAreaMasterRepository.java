package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.SegmentMaster;
import com.solar.model.SubAreaMaster;
@Repository
@Transactional
public interface SubAreaMasterRepository extends JpaRepository<SubAreaMaster, Integer>{

}
