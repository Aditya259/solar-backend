package com.solar.model;

public class FilterData {

	private String customerName;
	private String sector;
	private String subsidary;
	private String company;
	private String customerCode;
	private String type;
	private String purpose;
	private String employee;
	private String country;
	private String fromDate;
	private String toDate;
	private String enquiryId;
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(String enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSubsidary() {
		return subsidary;
	}

	public void setSubsidary(String subsidary) {
		this.subsidary = subsidary;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "FilterData [customerName=" + customerName + ", sector=" + sector + ", subsidary=" + subsidary
				+ ", company=" + company + ", customerCode=" + customerCode + ", type=" + type + ", purpose=" + purpose
				+ ", employee=" + employee + ", country=" + country + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", enquiryId=" + enquiryId + "]";
	}

}
