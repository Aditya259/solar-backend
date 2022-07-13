package com.solar.service;

import org.springframework.stereotype.Repository;

import com.solar.model.ComplaintAttachments;
import com.solar.model.Complaints;

@Repository
public interface ComplaintService {
	
	Complaints getComplaintSimple(int id);
	 Complaints getComplaint(int id);
	 ComplaintAttachments getComplaintsAttachmentJSON(String complaintsAttachmentJSON);
	
	

}
