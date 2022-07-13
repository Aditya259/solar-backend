package com.solar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.ComplaintAttachments;

@Repository
@Transactional
public interface ComplaintAttachmentsRepository extends JpaRepository<ComplaintAttachments, Integer>{
	
	List<ComplaintAttachments> findBycomplaintId(String complaintID);

}
