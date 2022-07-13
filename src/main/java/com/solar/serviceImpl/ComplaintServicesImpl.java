package com.solar.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.solar.model.ComplaintAttachments;
import com.solar.model.Complaints;
import com.solar.repository.AdminRepository;
import com.solar.repository.ComplaintAttachmentsRepository;
import com.solar.repository.ComplaintRepository;
import com.solar.repository.DepartmentRepository;
import com.solar.repository.FeedbackRepository;
import com.solar.service.ComplaintService;
import com.solar.usefullClasses.ComplaintDetails;

@Service
public class ComplaintServicesImpl implements ComplaintService {
	
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

	public Complaints getComplaintsJSON(String complaintsJSON) {
		Complaints complaints = new Complaints();
		try {
			Gson gson = new Gson();
			complaints = gson.fromJson(complaintsJSON, Complaints.class); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return complaints;
	}

	public Complaints getComplaintSimple(int id) {
		Optional<Complaints> opt = complaintRepository.findById(id);
		Complaints complaints = opt.get();
		return complaints;
	}

	public Complaints getComplaint(int id) {
		Optional<Complaints> opt = complaintRepository.findById(id);
		Complaints complaints = opt.get();
		complaints.setUploadAttachment(FileStorageServiceImpl.ImagePath(complaints.getUploadAttachment()));
		complaints.setUploadRe1(FileStorageServiceImpl.ImagePath(complaints.getUploadRe1()));
		complaints.setUploadRe2(FileStorageServiceImpl.ImagePath(complaints.getUploadRe2()));
		complaints.setUploadAttachment1(FileStorageServiceImpl.ImagePath(complaints.getUploadAttachment1()));
		complaints.setUploadAttachment2(FileStorageServiceImpl.ImagePath(complaints.getUploadAttachment2()));
		complaints.setUploadAttachment3(FileStorageServiceImpl.ImagePath(complaints.getUploadAttachment3()));
		complaints.setUploadAttachment4(FileStorageServiceImpl.ImagePath(complaints.getUploadAttachment4()));
		complaints
				.setComplaintReportAttachment(FileStorageServiceImpl.ImagePath(complaints.getComplaintReportAttachment()));
		complaints.setInChargeSignature(FileStorageServiceImpl.ImagePath(complaints.getInChargeSignature()));
		complaints.setHodSignature(FileStorageServiceImpl.ImagePath(complaints.getHodSignature()));
		complaints.setClouserAttachment(FileStorageServiceImpl.ImagePath(complaints.getClouserAttachment()));
		return complaints;
	}

//	Complaint Attachments

	public ComplaintAttachments getComplaintsAttachmentJSON(String complaintsAttachmentJSON) {
		ComplaintAttachments complaintSattachment = new ComplaintAttachments();
		try {
			Gson gson = new Gson();
			complaintSattachment = gson.fromJson(complaintsAttachmentJSON, ComplaintAttachments.class); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return complaintSattachment;
	}

	public List<ComplaintAttachments> getComplaintAttachments(String id) {
		List<ComplaintAttachments> list = complaintAttachmentsRepository.findBycomplaintId(id);
		for (int i = 0; i < list.size(); i++) {
			ComplaintAttachments complaintAttachments = list.get(i);
			complaintAttachments.setAttachment(FileStorageServiceImpl.ImagePath(complaintAttachments.getAttachment()));
		}
		return list;
	}

//	Complaint details Report
	public List<ComplaintDetails> getComplaintsDetails(Complaints complaint) {
		List<ComplaintDetails> complaintDetails = new ArrayList<ComplaintDetails>();
		List<Complaints> CList = complaintRepository.findByCompanyIdAndDateOfCreateBetweenOrderByIdDesc(
				complaint.getCompanyId(), complaint.getFromDate(), complaint.getToDate());
		List<String> pList = new ArrayList<>();

//		List for Distinct products
		for (Complaints Complaint : CList) {
			List<String> products = new ArrayList<>(Arrays.asList(Complaint.getNameOfProduct()));
			for (int j = 0; j < products.size(); j++) {
				if (pList.contains(products.get(j)) || Complaint.getNameOfProduct() == null) {
				} else {
					pList.add(products.get(j));
				}
			}
		}
		int i = 1;
		for (String prodName : pList) {
			int PH = 0, SP = 0, M = 0, B = 0, L = 0, S = 0, Total = 0;
			List<Complaints> SCList = CList.stream().filter(c -> Arrays.asList(c.getNameOfProduct()).contains(prodName))
					.collect(Collectors.toList());
			for (Complaints Com : SCList) {
				switch (Com.getType()) {

				case "Physical Condition":
					++PH;
					break;

				case "Strength / Performance":
					++SP;
					break;

				case "Misfire":
					++M;
					break;

				case "Boxes":
					++B;
					break;

				case "Labelling":
					++L;
					break;

				case "Safety":
					++S;
					break;

				default:
					break;
				}
			}
			Total = PH + SP + M + B + L + S;

			ComplaintDetails complaintDetail = new ComplaintDetails();
			complaintDetail.setId(i++);
			complaintDetail.setProduct(prodName);
			complaintDetail.setPhysicalCondition(PH);
			complaintDetail.setStrenthPerformance(SP);
			complaintDetail.setMisfire(M);
			complaintDetail.setBoxes(B);
			complaintDetail.setLabelling(L);
			complaintDetail.setSafety(S);
			complaintDetail.setTotal(Total);

			complaintDetails.add(complaintDetail);
		}
		return complaintDetails;
	}
}
