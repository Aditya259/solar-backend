package com.solar.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.model.Admins;
import com.solar.repository.AdminRepository;
import com.solar.repository.ComplaintAttachmentsRepository;
import com.solar.repository.ComplaintRepository;
import com.solar.repository.DepartmentRepository;
import com.solar.repository.FeedbackRepository;
import com.solar.service.AdminService;

@Service
public class AdminServicesImpl  implements AdminService{
	
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

	public Admins getAdmin(int id) {
		Optional<Admins> opt = adminRepository.findById(id);
		Admins admins = opt.get();
		return admins;
	}

	public void addAdmin(Admins admins) {
		adminRepository.save(admins);
	}
}
