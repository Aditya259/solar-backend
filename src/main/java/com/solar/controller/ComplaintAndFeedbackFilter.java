package com.solar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solar.model.Complaints;
import com.solar.model.Feedbacks;
import com.solar.repository.ComplaintRepository;
import com.solar.serviceImpl.ComplaintAndFeedbackFilterServiceImpl;
import com.solar.utils.PropertyValues;
import com.solar.voObject.ResponseVo;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComplaintAndFeedbackFilter {

	@Autowired(required=true)
	ComplaintAndFeedbackFilterServiceImpl complaintAndFeedbackFilterServiceImpl;

	@Autowired
	ComplaintRepository complaintRepository;

	@Autowired
	PropertyValues propertyValues;

	@GetMapping("/complaintsFilter")
	public ResponseVo complaintsFilter(@RequestParam("customerId") String customerId,
			@RequestParam("nameOfOrganization") String nameOfOrganization,
			@RequestParam("categoryOfComplaint") String categoryOfComplaint,
			@RequestParam("nameOfProduct") String nameOfProduct, @RequestParam("type") String type,
			@RequestParam("approvalStageDepartment") String approvalStageDepartment,
			@RequestParam("companyId") String companyId, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		ResponseVo response = new ResponseVo();
		try {
			List<Complaints> complaintsFinal = complaintAndFeedbackFilterServiceImpl.complaintsFilter(customerId,
					nameOfOrganization, categoryOfComplaint, approvalStageDepartment, companyId, fromDate, toDate,
					nameOfProduct);

			if (complaintsFinal.isEmpty()) {
				response.setStatus(false);
				response.setError(propertyValues.getErrorMessage());
				return response;
			} else {
				response.setCount(complaintsFinal.size());
				response.setData(complaintsFinal);
				response.setStatus(true);
				return response;
			}
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@GetMapping("/feedbacksFilter")
	public ResponseVo feedbacksFilter(@RequestParam("companyId") String companyId,
			@RequestParam("nameOfMine") String nameOfMine, @RequestParam("sectorId") String sectorId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		ResponseVo response = new ResponseVo();
		try {
			List<Feedbacks> feedBacksFinal = complaintAndFeedbackFilterServiceImpl.feedbacksFilter(companyId,
					nameOfMine, sectorId, fromDate, toDate);
			if (feedBacksFinal.isEmpty()) {
				response.setStatus(false);
				response.setError(propertyValues.getErrorMessage());
				return response;
			} else {
				response.setCount(feedBacksFinal.size());
				response.setData(feedBacksFinal);
				response.setStatus(true);
				return response;
			}

		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

}
