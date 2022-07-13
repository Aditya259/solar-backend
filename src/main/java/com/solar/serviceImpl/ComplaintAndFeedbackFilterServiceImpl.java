package com.solar.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.solar.model.Complaints;
import com.solar.model.Feedbacks;
import com.solar.repository.ComplaintRepository;
import com.solar.repository.FeedbackRepository;
import com.solar.service.ComplaintAndFeedbackFilterService;

@Component
public class ComplaintAndFeedbackFilterServiceImpl implements ComplaintAndFeedbackFilterService {

	@Autowired
	ComplaintServicesImpl complaintServices;

	@Autowired
	ComplaintRepository complaintRepository;

	@Autowired
	FeedBackServicesImpl feedBackServices;

	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	public boolean complaintsFilterEmpltyCheck(String complaintsJSON) {
		if (complaintsJSON.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public String createJsonFormat(String customerId, String nameOfOrganization, String categoryOfComplaint,
			String approvalStageDepartment, String companyId, String fromDate, String toDate) {
		JSONObject jsonResponse=new JSONObject();    
		jsonResponse.put("v", nullToEmpty(customerId));
		jsonResponse.put("nameOfOrganization",nullToEmpty(nameOfOrganization) );
		jsonResponse.put("categoryOfComplaint",nullToEmpty(categoryOfComplaint));
		jsonResponse.put("approvalStageDepartment",nullToEmpty(approvalStageDepartment));
		jsonResponse.put("companyId",nullToEmpty(companyId));
		jsonResponse.put("fromDate",fromDate);
		jsonResponse.put("toDate",toDate);
		return jsonResponse.toString();
	}

	@Override
	public List<Complaints> complaintsFilter(String customerId, String nameOfOrganization, String categoryOfComplaint,
			String approvalStageDepartment, String companyId, String fromDate, String toDate, String nameOfProduct) {
		List<Complaints> complaints = new ArrayList<Complaints>();
		String complaintsJSON = createJsonFormat(customerId, nameOfOrganization, categoryOfComplaint,
				approvalStageDepartment, companyId, fromDate, toDate);
		Boolean complaintsEmptyCheck = complaintsFilterEmpltyCheck(complaintsJSON);
		if (complaintsEmptyCheck) {
			return complaints;
		}
		Complaints complaintData = complaintServices.getComplaintsJSON(complaintsJSON);
		if (complaintData.getFromDate() != null && complaintData.getToDate() != null) {
			complaints = complaintRepository.findByDateOfCreateBetweenOrderByIdDesc(complaintData.getFromDate(),
					complaintData.getToDate());
		} else {
			complaints = complaintRepository.findAllByOrderByIdDesc();
		}
		complaints = complaints.stream()
				.filter(p -> complaintData.getCustomerId().isEmpty()
						|| nullToEmpty(p.getCustomerId()).equals(complaintData.getCustomerId()))
				.filter(p -> complaintData.getNameOfOrganization().isEmpty()
						|| nullToEmpty(p.getNameOfOrganization()).equals(complaintData.getNameOfOrganization()))
				.filter(p -> complaintData.getCategoryOfComplaint().isEmpty()
						|| nullToEmpty(p.getCategoryOfComplaint()).equals(complaintData.getCategoryOfComplaint()))
				.filter(p -> nameOfProduct.isEmpty() || Arrays.toString(p.getNameOfProduct()).contains(nameOfProduct))
				.filter(p -> complaintData.getType().isEmpty()
						|| nullToEmpty(p.getType()).equals(complaintData.getType()))
				.filter(p -> complaintData.getApprovalStageDepartment().isEmpty()
						|| nullToEmpty(p.getApprovalStageDepartment())
								.equals(complaintData.getApprovalStageDepartment()))
				.filter(p -> complaintData.getCompanyId().isEmpty()
						|| nullToEmpty(p.getCompanyId()).equals(complaintData.getCompanyId()))
				.collect(Collectors.toList());

		return complaints;
	}

	private Object nullToEmpty(Object s) {
		return s == null ? "" : s;
	}

	@Override
	public List<Feedbacks> feedbacksFilter(String companyId, String nameOfMine, String sectorId, String fromDate,
			String toDate) {

		JSONObject feedBackJSON = new JSONObject();
		feedBackJSON.put("companyID", nullToEmpty(companyId));
		feedBackJSON.put("nameOfMine", nullToEmpty(nameOfMine));
		feedBackJSON.put("sectorID", nullToEmpty(sectorId));
		feedBackJSON.put("toDate", toDate);

		List<Feedbacks> feedbacks = new ArrayList<Feedbacks>();
		if (feedBackJSON.isEmpty()) {
			return feedbacks;
		}

		Feedbacks FData = feedBackServices.getFeedbacksJSON(feedBackJSON.toString());

		FData.setSectorId(FData.getSectorId() == null ? 0 : FData.getSectorId());

		if (FData.getFromDate() != null && FData.getToDate() != null) {
			feedbacks = feedbackRepository.findByDateOfCreateBetweenOrderByIdDesc(FData.getFromDate(),
					FData.getToDate());
		} else {
			feedbacks = feedbackRepository.findAllByOrderByIdDesc();
		}

		feedbacks = feedbacks.stream()
				.filter(p -> FData.getCompanyId().isEmpty()
						|| nullToEmpty(p.getCompanyId()).equals(FData.getCompanyId()))
				.filter(p -> FData.getNameOfMine().isEmpty()
						|| nullToEmpty(p.getNameOfMine()).equals(FData.getNameOfMine()))
				.filter(p -> FData.getSectorId().equals(0) || nullToEmpty(p.getSectorId()).equals(FData.getSectorId()))
				.collect(Collectors.toList());

		return feedbacks;
	}

}
