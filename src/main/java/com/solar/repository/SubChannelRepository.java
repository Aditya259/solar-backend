package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.IndustryMaster;
import com.solar.model.SubChannelMaster;

@Repository
@Transactional
public interface SubChannelRepository extends JpaRepository<SubChannelMaster, Integer>{

}
