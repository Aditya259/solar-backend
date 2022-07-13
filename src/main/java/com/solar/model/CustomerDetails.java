package com.solar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_details")
public class CustomerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "company")
	private String company;

	@Column(name = "segment")
	private String segment;

	@Column(name = "industry")
	private String industry;

	@Column(name = "parentcompany")
	private String parentCompany;

	@Column(name = "subsidarycompany")
	private String subsidaryCompany;

	@Column(name = "subsidaryarea")
	private String subsidaryArea;

	@Column(name = "subsidarychannel")
	private String subsidaryChannel;

	@Column(name = "customername")
	private String customerName;

	@Column(name = "existingflag")
	private String existingFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getParentCompany() {
		return parentCompany;
	}

	public void setParentCompany(String parentCompany) {
		this.parentCompany = parentCompany;
	}

	public String getSubsidaryCompany() {
		return subsidaryCompany;
	}

	public void setSubsidaryCompany(String subsidaryCompany) {
		this.subsidaryCompany = subsidaryCompany;
	}

	public String getSubsidaryArea() {
		return subsidaryArea;
	}

	public void setSubsidaryArea(String subsidaryArea) {
		this.subsidaryArea = subsidaryArea;
	}

	public String getSubsidaryChannel() {
		return subsidaryChannel;
	}

	public void setSubsidaryChannel(String subsidaryChannel) {
		this.subsidaryChannel = subsidaryChannel;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getExistingFlag() {
		return existingFlag;
	}

	public void setExistingFlag(String existingFlag) {
		this.existingFlag = existingFlag;
	}

}
