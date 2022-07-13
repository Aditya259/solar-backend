package com.solar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solar.repository.AdminRepository;
import com.solar.repository.ComplaintRepository;
import com.solar.repository.DepartmentRepository;
import com.solar.repository.FeedbackRepository;
import com.solar.voObject.DashboardVo;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DashboardController {
	
	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	AdminRepository adminRepository;

	@GetMapping("/dashboardData")
	public DashboardVo dashboardData() {
		DashboardVo response = new DashboardVo();
		try {
			response.setTotalComplaints(complaintRepository.count());
			response.setTotalDepartments(departmentRepository.count());
			response.setTotalFeedback(feedbackRepository.count());
			response.setTotalAdmins(adminRepository.count());
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
