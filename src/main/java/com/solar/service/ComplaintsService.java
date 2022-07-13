package com.solar.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import com.solar.model.Complaints;

public interface ComplaintsService {
	Complaints addComplaint(String complaintsJSON,MultipartFile upload_attachment,MultipartFile uploadRe11,MultipartFile upload_re_12);
	Complaints complaintSalePersonUpdate(MultipartFile upload_attachment,MultipartFile upload_re_11, MultipartFile upload_re_12,String id,String complaintsJSON);
	void complaintUpdateLevel(Complaints complaints,String id);
	void complaintUpdateLevel(String complaintsJSON,MultipartFile uploadAttachment1,MultipartFile uploadAttachment2,MultipartFile uploadAttachment3,MultipartFile uploadAttachment4,String id);
	void complaintUpdatelevelReport(String complaintsJSON,String id,MultipartFile hodSignature,MultipartFile inChargeSignature, MultipartFile complaintReportAttachment);
	void complaintUpdateAssignSellPerson(Complaints complaints,String id);
	void complaintUpdateLevel(MultipartFile clouserAttachment,String complaintsJSON,String id);
	void complaintAddAttachments(MultipartFile attachment,String complaintsAttachmentJSON);
	Workbook generateExcelReport();


}
