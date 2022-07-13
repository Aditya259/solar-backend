package com.solar.service;

import org.apache.poi.ss.usermodel.Workbook;

import com.solar.model.Complaints;

public interface ComplaintsReportsService {
	
	Workbook compliantReportYearly(String complaintsJSON,Complaints complaint);
	Workbook compliantReportYearlyDetails(String complaintsJSON,Complaints complaint);

}
