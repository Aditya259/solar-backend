package com.solar.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "customer_data")
public class CustData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "cust_name")
	private String custName;

	@Column(name = "state")
	private String state;

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

	@Column(name = "sector")
	private String sector;

	@Column(name = "cpd_company")
	private String cpdCompany;

	@Column(name = "cpd_contact_person")
	private String cpdContactPerson;

	@Column(name = "cpd_designation")
	private String cpdDesignation;

	@Column(name = "cpd_email")
	private String cpdeEail;

	@Column(name = "ext_log_date")
	private Date extLogDate;

	@Column(name = "ext_log_time")
	private String extLogTime;

	@Column(name = "ext_subject")
	private String extSubject;

	@Column(name = "ext_contact_person")
	private String extContactPerson;

	@Column(name = "ext_contact_number")
	private String extContactNumber;

	@Column(name = "ext_description")
	private String extDescription;

	@Column(name = "ext_purpose_of_email")
	private String extPurposeOfEmail;

	@Column(name = "employeeName")
	private String employeeName;

	@Transient
	@Column(name = "attachmet")
	private String attachmet;

	@Transient
	private String dateToDisplay;

	@Transient
	private String custCodeWithCustName;

	@Column(name = "country")
	private String country;

	@ElementCollection
	@Column(name = "ext_mode_of_contact")
	private List<String> extModeOfContact;

	@Column(name = "enquiryId")
	private String enquiryId;

	@Column(name = "customerCode")
	private String customerCode;

	private Date selectedcalldatePicker;
	private String selectedcallSubject;
	private String selectedcallContactPerson;
	private String selectedCallContactNo;
	private String selectedCallPurposeOfEmail;
	private String selectedCallDescription;

	private Date selectedSmsdatePicker;
	private String selectedSmsSubject;
	private String selectedSmsContactPerson;
	private String selectedSmsContactNo;
	private String selectedSmsPurposeOfEmail;
	private String selectedSmsDescription;

	private Date selectedSvidatePicker;
	private String selectedSviSubject;
	private String selectedSviContactPerson;
	private String selectedSviContactNo;
	private String selectedSviPurposeOfEmail;
	private String selectedSviDescription;

	private Date selectedOmidatePicker;
	private String selectedOmiSubject;
	private String selectedOmiContactPerson;
	private String selectedOmiContactNo;
	private String selectedOmiPurposeOfEmail;
	private String selectedOmiDescription;

	private String modeOfContactEmail;
	private String modeOfContactCall;
	private String modeOfContactSms;
	private String modeOfContactSiteVisit;
	private String modeOfContactOnlineMeeting;

	@Column(name = "emailattachments")
	private String fileNameEmail;

	@Column(name = "callattachments")
	private String fileNameCall;

	@Column(name = "smsattachments")
	private String fileNameSms;

	@Column(name = "svattachments")
	private String fileNameSv;

	@Column(name = "omattachments")
	private String fileNameOm;

	public String getFileNameEmail() {
		return fileNameEmail;
	}

	public void setFileNameEmail(String fileNameEmail) {
		this.fileNameEmail = fileNameEmail;
	}

	public String getFileNameCall() {
		return fileNameCall;
	}

	public void setFileNameCall(String fileNameCall) {
		this.fileNameCall = fileNameCall;
	}

	public String getFileNameSms() {
		return fileNameSms;
	}

	public void setFileNameSms(String fileNameSms) {
		this.fileNameSms = fileNameSms;
	}

	public String getFileNameSv() {
		return fileNameSv;
	}

	public void setFileNameSv(String fileNameSv) {
		this.fileNameSv = fileNameSv;
	}

	public String getFileNameOm() {
		return fileNameOm;
	}

	public void setFileNameOm(String fileNameOm) {
		this.fileNameOm = fileNameOm;
	}

	

	public String getModeOfContactEmail() {
		return modeOfContactEmail;
	}

	public void setModeOfContactEmail(String modeOfContactEmail) {
		this.modeOfContactEmail = modeOfContactEmail;
	}

	public String getModeOfContactCall() {
		return modeOfContactCall;
	}

	public void setModeOfContactCall(String modeOfContactCall) {
		this.modeOfContactCall = modeOfContactCall;
	}

	public String getModeOfContactSms() {
		return modeOfContactSms;
	}

	public void setModeOfContactSms(String modeOfContactSms) {
		this.modeOfContactSms = modeOfContactSms;
	}

	public String getModeOfContactSiteVisit() {
		return modeOfContactSiteVisit;
	}

	public void setModeOfContactSiteVisit(String modeOfContactSiteVisit) {
		this.modeOfContactSiteVisit = modeOfContactSiteVisit;
	}

	public String getModeOfContactOnlineMeeting() {
		return modeOfContactOnlineMeeting;
	}

	public void setModeOfContactOnlineMeeting(String modeOfContactOnlineMeeting) {
		this.modeOfContactOnlineMeeting = modeOfContactOnlineMeeting;
	}

	public Date getSelectedcalldatePicker() {
		return selectedcalldatePicker;
	}

	public void setSelectedcalldatePicker(Date selectedcalldatePicker) {
		this.selectedcalldatePicker = selectedcalldatePicker;
	}

	public String getSelectedcallSubject() {
		return selectedcallSubject;
	}

	public void setSelectedcallSubject(String selectedcallSubject) {
		this.selectedcallSubject = selectedcallSubject;
	}

	public String getSelectedcallContactPerson() {
		return selectedcallContactPerson;
	}

	public void setSelectedcallContactPerson(String selectedcallContactPerson) {
		this.selectedcallContactPerson = selectedcallContactPerson;
	}

	public String getSelectedCallContactNo() {
		return selectedCallContactNo;
	}

	public void setSelectedCallContactNo(String selectedCallContactNo) {
		this.selectedCallContactNo = selectedCallContactNo;
	}

	public String getSelectedCallPurposeOfEmail() {
		return selectedCallPurposeOfEmail;
	}

	public void setSelectedCallPurposeOfEmail(String selectedCallPurposeOfEmail) {
		this.selectedCallPurposeOfEmail = selectedCallPurposeOfEmail;
	}

	public String getSelectedCallDescription() {
		return selectedCallDescription;
	}

	public void setSelectedCallDescription(String selectedCallDescription) {
		this.selectedCallDescription = selectedCallDescription;
	}

	public Date getSelectedSmsdatePicker() {
		return selectedSmsdatePicker;
	}

	public void setSelectedSmsdatePicker(Date selectedSmsdatePicker) {
		this.selectedSmsdatePicker = selectedSmsdatePicker;
	}

	public String getSelectedSmsSubject() {
		return selectedSmsSubject;
	}

	public void setSelectedSmsSubject(String selectedSmsSubject) {
		this.selectedSmsSubject = selectedSmsSubject;
	}

	public String getSelectedSmsContactPerson() {
		return selectedSmsContactPerson;
	}

	public void setSelectedSmsContactPerson(String selectedSmsContactPerson) {
		this.selectedSmsContactPerson = selectedSmsContactPerson;
	}

	public String getSelectedSmsContactNo() {
		return selectedSmsContactNo;
	}

	public void setSelectedSmsContactNo(String selectedSmsContactNo) {
		this.selectedSmsContactNo = selectedSmsContactNo;
	}

	public String getSelectedSmsPurposeOfEmail() {
		return selectedSmsPurposeOfEmail;
	}

	public void setSelectedSmsPurposeOfEmail(String selectedSmsPurposeOfEmail) {
		this.selectedSmsPurposeOfEmail = selectedSmsPurposeOfEmail;
	}

	public String getSelectedSmsDescription() {
		return selectedSmsDescription;
	}

	public void setSelectedSmsDescription(String selectedSmsDescription) {
		this.selectedSmsDescription = selectedSmsDescription;
	}

	public Date getSelectedSvidatePicker() {
		return selectedSvidatePicker;
	}

	public void setSelectedSvidatePicker(Date selectedSvidatePicker) {
		this.selectedSvidatePicker = selectedSvidatePicker;
	}

	public String getSelectedSviSubject() {
		return selectedSviSubject;
	}

	public void setSelectedSviSubject(String selectedSviSubject) {
		this.selectedSviSubject = selectedSviSubject;
	}

	public String getSelectedSviContactPerson() {
		return selectedSviContactPerson;
	}

	public void setSelectedSviContactPerson(String selectedSviContactPerson) {
		this.selectedSviContactPerson = selectedSviContactPerson;
	}

	public String getSelectedSviContactNo() {
		return selectedSviContactNo;
	}

	public void setSelectedSviContactNo(String selectedSviContactNo) {
		this.selectedSviContactNo = selectedSviContactNo;
	}

	public String getSelectedSviPurposeOfEmail() {
		return selectedSviPurposeOfEmail;
	}

	public void setSelectedSviPurposeOfEmail(String selectedSviPurposeOfEmail) {
		this.selectedSviPurposeOfEmail = selectedSviPurposeOfEmail;
	}

	public String getSelectedSviDescription() {
		return selectedSviDescription;
	}

	public void setSelectedSviDescription(String selectedSviDescription) {
		this.selectedSviDescription = selectedSviDescription;
	}

	public Date getSelectedOmidatePicker() {
		return selectedOmidatePicker;
	}

	public void setSelectedOmidatePicker(Date selectedOmidatePicker) {
		this.selectedOmidatePicker = selectedOmidatePicker;
	}

	public String getSelectedOmiSubject() {
		return selectedOmiSubject;
	}

	public void setSelectedOmiSubject(String selectedOmiSubject) {
		this.selectedOmiSubject = selectedOmiSubject;
	}

	public String getSelectedOmiContactPerson() {
		return selectedOmiContactPerson;
	}

	public void setSelectedOmiContactPerson(String selectedOmiContactPerson) {
		this.selectedOmiContactPerson = selectedOmiContactPerson;
	}

	public String getSelectedOmiContactNo() {
		return selectedOmiContactNo;
	}

	public void setSelectedOmiContactNo(String selectedOmiContactNo) {
		this.selectedOmiContactNo = selectedOmiContactNo;
	}

	public String getSelectedOmiPurposeOfEmail() {
		return selectedOmiPurposeOfEmail;
	}

	public void setSelectedOmiPurposeOfEmail(String selectedOmiPurposeOfEmail) {
		this.selectedOmiPurposeOfEmail = selectedOmiPurposeOfEmail;
	}

	public String getSelectedOmiDescription() {
		return selectedOmiDescription;
	}

	public void setSelectedOmiDescription(String selectedOmiDescription) {
		this.selectedOmiDescription = selectedOmiDescription;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCustCodeWithCustName() {
		return custCodeWithCustName;
	}

	public void setCustCodeWithCustName(String custCodeWithCustName) {
		this.custCodeWithCustName = custCodeWithCustName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(String enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

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

	public String getCpdCompany() {
		return cpdCompany;
	}

	public void setCpdCompany(String cpdCompany) {
		this.cpdCompany = cpdCompany;
	}

	public String getCpdContactPerson() {
		return cpdContactPerson;
	}

	public void setCpdContactPerson(String cpdContactPerson) {
		this.cpdContactPerson = cpdContactPerson;
	}

	public String getCpdDesignation() {
		return cpdDesignation;
	}

	public void setCpdDesignation(String cpdDesignation) {
		this.cpdDesignation = cpdDesignation;
	}

	public String getCpdeEail() {
		return cpdeEail;
	}

	public void setCpdeEail(String cpdeEail) {
		this.cpdeEail = cpdeEail;
	}

	public Date getExtLogDate() {
		return extLogDate;
	}

	public void setExtLogDate(Date extLogDate) {
		this.extLogDate = extLogDate;
	}

	public String getExtLogTime() {
		return extLogTime;
	}

	public void setExtLogTime(String extLogTime) {
		this.extLogTime = extLogTime;
	}

	public String getExtSubject() {
		return extSubject;
	}

	public void setExtSubject(String extSubject) {
		this.extSubject = extSubject;
	}

	public String getExtContactPerson() {
		return extContactPerson;
	}

	public void setExtContactPerson(String extContactPerson) {
		this.extContactPerson = extContactPerson;
	}

	public String getExtContactNumber() {
		return extContactNumber;
	}

	public void setExtContactNumber(String extContactNumber) {
		this.extContactNumber = extContactNumber;
	}

	public String getExtDescription() {
		return extDescription;
	}

	public void setExtDescription(String extDescription) {
		this.extDescription = extDescription;
	}

	public String getExtPurposeOfEmail() {
		return extPurposeOfEmail;
	}

	public void setExtPurposeOfEmail(String extPurposeOfEmail) {
		this.extPurposeOfEmail = extPurposeOfEmail;
	}

	public String getAttachmet() {
		return attachmet;
	}

	public void setAttachmet(String attachmet) {
		this.attachmet = attachmet;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getDateToDisplay() {
		return dateToDisplay;
	}

	public void setDateToDisplay(String dateToDisplay) {
		this.dateToDisplay = dateToDisplay;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public List<String> getExtModeOfContact() {
		return extModeOfContact;
	}

	public void setExtModeOfContact(List<String> extModeOfContact) {
		this.extModeOfContact = extModeOfContact;
	}

	@Override
	public String toString() {
		return "CustData [id=" + id + ", custName=" + custName + ", state=" + state + ", company=" + company
				+ ", segment=" + segment + ", industry=" + industry + ", parentCompany=" + parentCompany
				+ ", subsidaryCompany=" + subsidaryCompany + ", subsidaryArea=" + subsidaryArea + ", subsidaryChannel="
				+ subsidaryChannel + ", customerName=" + customerName + ", existingFlag=" + existingFlag + ", sector="
				+ sector + ", cpdCompany=" + cpdCompany + ", cpdContactPerson=" + cpdContactPerson + ", cpdDesignation="
				+ cpdDesignation + ", cpdeEail=" + cpdeEail + ", extLogDate=" + extLogDate + ", extLogTime="
				+ extLogTime + ", extSubject=" + extSubject + ", extContactPerson=" + extContactPerson
				+ ", extContactNumber=" + extContactNumber + ", extDescription=" + extDescription
				+ ", extPurposeOfEmail=" + extPurposeOfEmail + ", employeeName=" + employeeName + ", attachmet="
				+ attachmet + ", dateToDisplay=" + dateToDisplay + ", custCodeWithCustName=" + custCodeWithCustName
				+ ", country=" + country + ", extModeOfContact=" + extModeOfContact + ", enquiryId=" + enquiryId
				+ ", customerCode=" + customerCode + ", selectedcalldatePicker=" + selectedcalldatePicker
				+ ", selectedcallSubject=" + selectedcallSubject + ", selectedcallContactPerson="
				+ selectedcallContactPerson + ", selectedCallContactNo=" + selectedCallContactNo
				+ ", selectedCallPurposeOfEmail=" + selectedCallPurposeOfEmail + ", selectedCallDescription="
				+ selectedCallDescription + ", selectedSmsdatePicker=" + selectedSmsdatePicker + ", selectedSmsSubject="
				+ selectedSmsSubject + ", selectedSmsContactPerson=" + selectedSmsContactPerson
				+ ", selectedSmsContactNo=" + selectedSmsContactNo + ", selectedSmsPurposeOfEmail="
				+ selectedSmsPurposeOfEmail + ", selectedSmsDescription=" + selectedSmsDescription
				+ ", selectedSvidatePicker=" + selectedSvidatePicker + ", selectedSviSubject=" + selectedSviSubject
				+ ", selectedSviContactPerson=" + selectedSviContactPerson + ", selectedSviContactNo="
				+ selectedSviContactNo + ", selectedSviPurposeOfEmail=" + selectedSviPurposeOfEmail
				+ ", selectedSviDescription=" + selectedSviDescription + ", selectedOmidatePicker="
				+ selectedOmidatePicker + ", selectedOmiSubject=" + selectedOmiSubject + ", selectedOmiContactPerson="
				+ selectedOmiContactPerson + ", selectedOmiContactNo=" + selectedOmiContactNo
				+ ", selectedOmiPurposeOfEmail=" + selectedOmiPurposeOfEmail + ", selectedOmiDescription="
				+ selectedOmiDescription + ", modeOfContactEmail=" + modeOfContactEmail + ", modeOfContactCall="
				+ modeOfContactCall + ", modeOfContactSms=" + modeOfContactSms + ", modeOfContactSiteVisit="
				+ modeOfContactSiteVisit + ", modeOfContactOnlineMeeting=" + modeOfContactOnlineMeeting + "]";
	}

}
