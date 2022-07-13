package com.solar.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solar.model.Complaints;
import com.solar.repository.AdminRepository;
import com.solar.repository.ComplaintAttachmentsRepository;
import com.solar.repository.ComplaintRepository;
import com.solar.repository.DepartmentRepository;
import com.solar.repository.FeedbackRepository;
import com.solar.repository.FeedbacksQuarterlyTargetRepository;
import com.solar.repository.SectorRepository;
import com.solar.serviceImpl.AdminServicesImpl;
import com.solar.serviceImpl.ComplaintServicesImpl;
import com.solar.serviceImpl.ComplaintsReportsServiceImpl;
import com.solar.serviceImpl.DepartmentServicesImpl;
import com.solar.serviceImpl.FeedBackServicesImpl;
import com.solar.serviceImpl.FileStorageServiceImpl;
import com.solar.usefullClasses.DateFormat;
import com.solar.utils.PropertyValues;
import com.solar.voObject.ResponseVo;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ComplaintsReportsController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AdminServicesImpl adminServices;

	@Autowired
	public FileStorageServiceImpl fileStorageService;
	
	@Autowired
	ComplaintRepository complaintRepository
	;
	
	@Autowired
	ComplaintServicesImpl complaintServices;
	
	@Autowired
	ComplaintAttachmentsRepository complaintAttachmentsRepository;
	
	@Autowired
	FeedBackServicesImpl feedBackServices;
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DepartmentServicesImpl departmentServices;
	
	@Autowired
	SectorRepository sectorRepository;
	
	@Autowired
	FeedbacksQuarterlyTargetRepository feedbacksQuarterlyTargetRepository;
	
	@Autowired
	ComplaintsReportsServiceImpl complaintsReportsServiceImpl;
	
	@Autowired
	PropertyValues propertyValues;

	@GetMapping("/report/complaint/yearly")
	public ResponseEntity<Resource> compliantReportYearly(
			@RequestParam("companyId") String companyId,
			@RequestParam("companyName") String companyName,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) throws IOException {
		
		String complaintsJSON="{\r\n" + 
				"    \"companyId\":\""+companyId+"\",\r\n" + 
				"    \"companyName\":\""+companyName+"\",\r\n" + 
				"    \"fromDate\":\""+fromDate.toString()+"\",\r\n" + 
				"    \"toDate\":\""+toDate+"\"\r\n" + 
				"}";
		
		if(complaintsJSON.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
		Complaints complaint=complaintServices.getComplaintsJSON(complaintsJSON);
		
		Workbook wb = complaintsReportsServiceImpl.compliantReportYearly(complaintsJSON,complaint);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		wb.close();
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		String fileName="ComaplintReport_"+DateFormat.DateFormat2(complaint.getFromDate())+"-"+DateFormat.DateFormat2(complaint.getToDate())+".xlsx";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName+"");
		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);
		return response;
	}

	
	@GetMapping("/report/complaint/yearly/details")
	public ResponseEntity<Resource> compliantReportYearlyDetails(
			@RequestParam("companyId") String companyId,
			@RequestParam("companyName") String companyName,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) throws IOException {
		
		String complaintsJSON="{\r\n" + 
				"    \"companyId\":\""+companyId+"\",\r\n" + 
				"    \"companyName\":\""+companyName+"\",\r\n" + 
				"    \"fromDate\":\""+fromDate.toString()+"\",\r\n" + 
				"    \"toDate\":\""+toDate+"\"\r\n" + 
				"}";
		
		if(complaintsJSON.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
		Complaints complaint=complaintServices.getComplaintsJSON(complaintsJSON);
		
		Workbook wb = complaintsReportsServiceImpl.compliantReportYearlyDetails(complaintsJSON,complaint);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		wb.write(os);
		wb.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		String fileName="ComaplintDetailsReport_"+DateFormat.DateFormat2(complaint.getFromDate())+"-"+DateFormat.DateFormat2(complaint.getToDate())+".xlsx";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName+"");
		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);
		return response;
	}

	
	@GetMapping("/report/complaint/yearly/details/chartData")
	public ResponseVo compliantReportYearlyDetailsChart(
			@RequestParam("companyId") String companyId,
			@RequestParam("companyName") String companyName,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) throws IOException {
		ResponseVo response = new ResponseVo();
		JSONObject complaintsJSON=new JSONObject();    
		complaintsJSON.put("companyId", companyId);
		complaintsJSON.put("companyName", companyName);
		complaintsJSON.put("fromDate", fromDate.toString());
		complaintsJSON.put("toDate", toDate);
		
		if(complaintsJSON.isEmpty()) {
			response.setStatus(false);
			response.setError(propertyValues.getErrorMessage());
			return response;
		}
		try {
			Complaints complaint=complaintServices.getComplaintsJSON(complaintsJSON.toString());		
			response.setData(complaintServices.getComplaintsDetails(complaint));
			response.setStatus(true);
			return response;
    	} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}
	
}
