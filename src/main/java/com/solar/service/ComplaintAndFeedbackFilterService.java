package com.solar.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.solar.model.Complaints;
import com.solar.model.Feedbacks;

@Repository
public interface ComplaintAndFeedbackFilterService {
	
	String createJsonFormat(String customerID,String nameOfOrganization ,String categoryOfComplaint,String approvalStageDepartment,String companyId,String fromDate,String toDate);
	
	boolean complaintsFilterEmpltyCheck(String responseValues);
	
	List<Complaints> complaintsFilter(String customerID,String nameOfOrganization ,String categoryOfComplaint,String approvalStageDepartment,String companyId,String fromDate,String toDate,String nameOfProduct);

	List<Feedbacks> feedbacksFilter(String companyID,String nameOfMine,String sectorID,String fromDate, String toDate);
	
	
}
