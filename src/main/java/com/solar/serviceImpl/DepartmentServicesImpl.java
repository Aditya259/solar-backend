package com.solar.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.model.Departments;
import com.solar.repository.AdminRepository;
import com.solar.repository.ComplaintAttachmentsRepository;
import com.solar.repository.ComplaintRepository;
import com.solar.repository.DepartmentRepository;
import com.solar.repository.FeedbackRepository;
import com.solar.service.DepartmentService;

@Service
public class DepartmentServicesImpl implements DepartmentService{
	
	@Autowired
	AdminRepository adminRepository;

	@Autowired
	ComplaintRepository complaintRepository;

	@Autowired
	FeedbackRepository feedbackRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	ComplaintAttachmentsRepository complaintAttachmentsRepository;

	public Departments getDepartment(int id) {
		Optional<Departments> opt= departmentRepository.findById(id);
		Departments departments=opt.get();
		return departments;
	}
	
	public Departments addDepartment(Departments departments) {
		return departmentRepository.save(departments);
	}
}
