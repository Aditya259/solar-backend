package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.TestImage;

@Repository
@Transactional
public interface TestImageRepository extends JpaRepository<TestImage, Integer>{

}
