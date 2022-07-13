package com.solar.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.solar.model.Feedbacks;
import com.solar.repository.AdminRepository;
import com.solar.repository.ComplaintAttachmentsRepository;
import com.solar.repository.ComplaintRepository;
import com.solar.repository.DepartmentRepository;
import com.solar.repository.FeedbackRepository;
import com.solar.service.FeedBackService;

@Service
public class FeedBackServicesImpl implements FeedBackService{
	
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

	public Feedbacks getFeedbacksJSON(String feedbackJSON) {
		Feedbacks feedbacks=new Feedbacks();
		try {
			Gson gson = new Gson();
			feedbacks = gson.fromJson(feedbackJSON, Feedbacks.class); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbacks;
	}
	
	public Feedbacks getFeedbacksSimple(int id) {
		Optional<Feedbacks> opt= feedbackRepository.findById(id);
		Feedbacks feedbacks=opt.get();
		return feedbacks;
	}
	
	public Feedbacks getFeedbacks(int id) {
		Optional<Feedbacks> opt= feedbackRepository.findById(id);
		Feedbacks feedbacks=opt.get();
		feedbacks.setSignature(FileStorageServiceImpl.ImagePath(feedbacks.getSignature()));
		feedbacks.setUploadAttachment(FileStorageServiceImpl.ImagePath(feedbacks.getUploadAttachment()));
		return feedbacks;
	}
	
	public List<Feedbacks> getFeedback(String date1,String date2,String date3,String companyID) {
		List<Feedbacks> feedbacks= feedbackRepository.findBYDateOfCreateAndCompanyid(date1, date2, date3,companyID);
		
		for(Feedbacks feedback:feedbacks) {
			int totalPoints=feedback.getProductQuality1()+feedback.getProductQuality2()+feedback.getDelivery1()+feedback.getDelivery2()+feedback.getDelivery3()+
					feedback.getService1()+feedback.getService2()+feedback.getService3()+feedback.getBehaviourOfOurStaff1()+feedback.getBehaviourOfOurStaff2()+feedback.getBehaviourOfOurStaff3()+
					feedback.getCompetitiveness1()+feedback.getCompetitiveness2();
			
			int percent = (totalPoints * 100 / 52) ;
			String overallPerformance="";
			if(0 <= percent && percent<=10) {
				overallPerformance="Not_Satisfactory";
			}
			if(11 <= percent && percent<=60) {
				overallPerformance="Satisfactory";
			}
			if(61 <= percent && percent<=80) {
				overallPerformance="Good";
			}
			if(81 <= percent && percent<=100) {
				overallPerformance="Excellent";
			}
			
			feedback.setTotalPoints(totalPoints);
			feedback.setPercent(percent);
			feedback.setOverallPerformance(overallPerformance);
			
		}
		return feedbacks;
	}
	
	public int CountFeedback(String sector,List<Feedbacks> feedbacks) {
		List<Feedbacks> feedbackData=feedbacks.stream().filter(p -> p.getSectorName().equals(sector)).collect(Collectors.toList());
		return feedbackData.size();
	}
}
