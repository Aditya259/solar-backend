package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.Admins;
import com.solar.model.FeedbacksQuarterlyTarget;

@Repository
@Transactional
public interface FeedbacksQuarterlyTargetRepository  extends JpaRepository<FeedbacksQuarterlyTarget, Integer>{

	FeedbacksQuarterlyTarget findByYear(String year);
}
