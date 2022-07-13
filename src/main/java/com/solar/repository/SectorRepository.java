package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.Sectors;

@Repository
@Transactional
public interface SectorRepository extends JpaRepository<Sectors, Integer>{

}
