package com.solar.usefullClasses;

import java.util.List;
import java.util.stream.Collectors;

import com.solar.model.Feedbacks;

public class FeedbackPerformance {

	private int id;
	private String name;
	private List<SectrorAndPercent> sectrorAndPercent;
	
	public FeedbackPerformance() {
		super();
	}
	
	public FeedbackPerformance(int id, String name,
			List<com.solar.usefullClasses.SectrorAndPercent> sectrorAndPercent) {
		super();
		this.id = id;
		this.name = name;
		this.sectrorAndPercent = sectrorAndPercent;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<SectrorAndPercent> getSectrorAndPercent()
	{
		return sectrorAndPercent;
	}

	public void setSectror_and_Percent(List<SectrorAndPercent> sectrorAndPercent) {
		this.sectrorAndPercent = sectrorAndPercent;
	}

	public static int FeedbackPerformancePercent(String sector,String opt,List<Feedbacks> feedbacks) {
		List<Feedbacks> feedbacks_fltr=feedbacks.stream().filter(p -> p.getSectorName().equals(sector)).collect(Collectors.toList());
		
		if(feedbacks_fltr.isEmpty()) {return 0;}
		
		int tot=0, n=0;int percent=0;
		switch (opt) {
		
		case "Product Quality":
			for (Feedbacks feed : feedbacks_fltr) {
				tot=tot+feed.getProductQuality1()+feed.getProductQuality2();
				n=n+8;
			}
		break;
		
		case "Delivery":
			for (Feedbacks feed : feedbacks_fltr) {
				tot=tot+feed.getDelivery1()+feed.getDelivery2()+feed.getDelivery3();
				n=n+12;
			}
		break;
		
		case "Services":
			for (Feedbacks feed : feedbacks_fltr) {
				tot=tot+feed.getService1()+feed.getService2()+feed.getService3();
				n=n+12;
			}
		break;
		
		case "Knowledge & behaviour of Staff":
			for (Feedbacks feed : feedbacks_fltr) {
				tot=tot+feed.getBehaviourOfOurStaff1()+feed.getBehaviourOfOurStaff2()+feed.getBehaviourOfOurStaff3();
				n=n+12;
			}
		break;
		
		case "Competitiveness":
			for (Feedbacks feed : feedbacks_fltr) {
				tot=tot+feed.getCompetitiveness1()+feed.getCompetitiveness2();
				n=n+8;
			}
		break;

		
		default:
			break;
		}
		
		percent=(tot*100)/n;
		
		return percent;
	}

	@Override
	public String toString() {
		return "FeedbackPerformance [id=" + id + ", name=" + name + ", SectrorAndPercent=" + sectrorAndPercent + "]";
	}
	
	
}
