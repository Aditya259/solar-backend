package com.solar.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.solar.model.Feedbacks;

@Repository
public interface FeedBackService {
	
	Feedbacks getFeedbacksJSON(String feedbackJSON);
	Feedbacks getFeedbacksSimple(int id);
	Feedbacks getFeedbacks(int id);
	List<Feedbacks> getFeedback(String date1,String date2,String date3,String companyID);
	int CountFeedback(String sector,List<Feedbacks> feedbacks);

}
