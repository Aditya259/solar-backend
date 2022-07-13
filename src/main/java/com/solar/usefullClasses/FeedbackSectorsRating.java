package com.solar.usefullClasses;

public class FeedbackSectorsRating {

	private String sectorName;
	private FeedbackSectorsRatingPercent feedbackSectorsRatingPercent;
	
	public FeedbackSectorsRating() {
		super();
	}

	public FeedbackSectorsRating(String sectorName,
			com.solar.usefullClasses.FeedbackSectorsRatingPercent feedbackSectorsRatingPercent) {
		super();
		this.sectorName = sectorName;
		this.feedbackSectorsRatingPercent = feedbackSectorsRatingPercent;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public FeedbackSectorsRatingPercent getFeedbackSectorsRatingPercent() {
		return feedbackSectorsRatingPercent;
	}

	public void setFeedbackSectorsRatingPercent(FeedbackSectorsRatingPercent feedbackSectorsRatingPercent) {
		this.feedbackSectorsRatingPercent = feedbackSectorsRatingPercent;
	}

	@Override
	public String toString() {
		return "FeedbackSectorsRating [sectorName=" + sectorName + ", feedbackSectorsRatingPercent="
				+ feedbackSectorsRatingPercent + "]";
	}

	
	
	
}
