package com.solar.usefullClasses;

public class FeedbackSectorsRatingPercent {

	private Integer productQuality;
	private Integer delivery;
	private Integer service;
	private Integer knowledgeAndBehaviorOfOurStaff;
	private Integer competitiveness;
	
	public FeedbackSectorsRatingPercent() {
		super();
	}

	public FeedbackSectorsRatingPercent(Integer productQuality, Integer delivery, Integer service,
			Integer knowledgeAndBehaviorOfOurStaff, Integer competitiveness) {
		super();
		this.productQuality = productQuality;
		this.delivery = delivery;
		this.service = service;
		this.knowledgeAndBehaviorOfOurStaff = knowledgeAndBehaviorOfOurStaff;
		this.competitiveness = competitiveness;
	}

	public Integer getProductQuality() {
		return productQuality;
	}

	public void setProductQuality(Integer productQuality) {
		this.productQuality = productQuality;
	}

	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

	public Integer getService() {
		return service;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	public Integer getKnowledgeandBehaviorOfOurStaff() {
		return knowledgeAndBehaviorOfOurStaff;
	}

	public void setgetKnowledgeandBehaviorOfOurStaff(Integer knowledgeAndBehaviorOfOurStaff) {
		this.knowledgeAndBehaviorOfOurStaff = knowledgeAndBehaviorOfOurStaff;
	}

	public Integer getCompetitiveness() {
		return competitiveness;
	}

	public void setCompetitiveness(Integer competitiveness) {
		this.competitiveness = competitiveness;
	}

	@Override
	public String toString() {
		return "FeedbackSectorsRatingPercent [productQuality=" + productQuality + ", delivery=" + delivery
				+ ", service=" + service + ", getKnowledgeandBehaviorOfOurStaff="
				+ knowledgeAndBehaviorOfOurStaff + ", competitiveness=" + competitiveness + "]";
	}

	
}
