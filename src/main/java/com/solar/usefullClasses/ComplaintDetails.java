package com.solar.usefullClasses;

public class ComplaintDetails {

	private int id;
	private String  product;
	private Integer physicalCondition;
	private Integer strenthPerformance;
	private Integer misfire;
	private Integer boxes;
	private Integer labelling;
	private Integer safety;
	private Integer total;
	
	public ComplaintDetails() {
		super();
	}
	
	public ComplaintDetails(int id, String product, Integer physicalCondition, Integer strenthPerformance,
			Integer misfire, Integer boxes, Integer labelling, Integer safety, Integer total) {
		super();
		this.id = id;
		this.product = product;
		this.physicalCondition = physicalCondition;
		this.strenthPerformance = strenthPerformance;
		this.misfire = misfire;
		this.boxes = boxes;
		this.labelling = labelling;
		this.safety = safety;
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Integer getPhysicalCondition() {
		return physicalCondition;
	}
	public void setPhysicalCondition(Integer physicalCondition) {
		this.physicalCondition = physicalCondition;
	}
	public Integer getStrenthPerformance() {
		return strenthPerformance;
	}
	public void setStrenthPerformance(Integer strenthPerformance) {
		this.strenthPerformance = strenthPerformance;
	}
	public Integer getMisfire() {
		return misfire;
	}
	public void setMisfire(Integer misfire) {
		this.misfire = misfire;
	}
	public Integer getBoxes() {
		return boxes;
	}
	public void setBoxes(Integer boxes) {
		this.boxes = boxes;
	}
	public Integer getLabelling() {
		return labelling;
	}
	public void setLabelling(Integer labelling) {
		this.labelling = labelling;
	}
	public Integer getSafety() {
		return safety;
	}
	public void setSafety(Integer safety) {
		this.safety = safety;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Complaint_Details [id=" + id + ", product=" + product + ", physicalCondition=" + physicalCondition
				+ ", strenthPerformance=" + strenthPerformance + ", misfire=" + misfire + ", boxes=" + boxes
				+ ", labelling=" + labelling + ", safety=" + safety + ", total=" + total + "]";
	}
	
	
	
}
