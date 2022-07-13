package com.solar.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:Messages.properties")
@ConfigurationProperties
public class PropertyValues {

	@Value("${errorMessage}")
	private String errorMessage;

	@Value("${File_Type_Error}")
	private String fieTypeError;

	@Value("${SUCCESS_MESSAGE}")
	private String successMessage;

	@Value("${FIELD_IS_EMPTY}")
	private String fieldIsEmpty;

	@Value("${ATTACHMENT_NOT_SELECTED}")
	private String attachmentNotSelected;

	@Value("${Departments_Already_Available}")
	private String departmentsAlreadyAvailable;

	@Value("${DepartmentNotFound}")
	private String departmentNotFound;

	@Value("${deleteNotSucess}")
	private String deleteNotSucess;

	@Value("${deleteSucess}")
	private String deleteSucess;

	@Value("${UpdateSucess}")
	private String updateSucess;

	@Value("${error}")
	private String error;

	@Value("${uploadAttachment}")
	private String uploadAttachment;

	@Value("${UserAlreadyAvailable}")
	private String userAlreadyAvailable;

	@Value("${LoginSuccess}")
	private String loginSuccess;
	
	
	@Value("${NatureofComplaint}")
	private String natureofComplaint;
	
	@Value("${slNo}")
	private String slNo;
	
	@Value("${ReportIDN0}")
	private String reportIDN0;

	@Value("${Product}")
	private String product;
	
	@Value("${CustomersName}")
	private String customersName;
	
	@Value("${DateofReceiptofcomplaint}")
	private String dateofReceiptofcomplaint;
	
	@Value("${BatchNoDOM}")
	private String batchNoDOM;
	
	@Value("${RemarksfromPlant}")
	private String remarksfromPlant;
	
	@Value("${RemarksFromTechnicalServices}")
	private String remarksFromTechnicalServices;
	
	@Value("${CustomerComplaintFrom}")
	private String customerComplaintFrom;
	
	@Value("${ComplaintDetailFrom}")
	private String complaintDetailFrom;
	
	@Value("${PhysicalCondition}")
	private String physicalCondition;
	
	@Value("${StrengthPerformance}")
	private String strengthPerformance;
	
	@Value("${Misfire}")
	private String misfire;
	
	@Value("${Boxes}")
	private String boxes;
	
	@Value("${Labelling}")
	private String Labelling;
	
	@Value("${Safety}")
	private String safety;
	
	@Value("${Total}")
	private String Total;
	
	
	@Value("${uploadAttachmentComplaints}")
	private String uploadAttachmentComplaints;
	
	@Value("${uploadRe1Complaints}")
	private String uploadRe1Complaints;
	

	@Value("${uploadRe2Complaints}")
	private String uploadRe2Complaints;
	
	
	@Value("${uploadAttachment1}")
	private String uploadAttachment1;
	
	@Value("${uploadAttachment2}")
	private String uploadAttachment2;
	
	@Value("${uploadAttachment3}")
	private String uploadAttachment3;
	
	@Value("${uploadAttachment4}")
	private String uploadAttachment4;
	
	@Value("${complaintReportAttachment}")
	private String complaintReportAttachment;
	
	@Value("${inChargeSignature}")
	private String inChargeSignature;
	
	@Value("${hodSignature}")
	private String hodSignature;
	
	@Value("${ComAttAttachments}")
	private String comAttAttachments;
	 
	@Value("${clouserAttachment}")
	private String clouserAttachment;
	
	
	public String getClouserAttachment() 
	{
		return clouserAttachment;
	}

	public String getComAttAttachments()
	{
		return comAttAttachments;
	}

	public String getHodSignature()
	{
		return hodSignature;
	}

	public String getInChargeSignature() 
	{
		return inChargeSignature;
	}

	public String getComplaintReportAttachment() 
	{
		return complaintReportAttachment;
	}

	public String getUploadAttachment1()
	{
		return uploadAttachment1;
	}

	public String getUploadAttachment2()
	{
		return uploadAttachment2;
	}

	public String getUploadAttachment3() 
	{
		return uploadAttachment3;
	}

	public String getUploadAttachment4() 
	{
		return uploadAttachment4;
	}

	public String getUploadRe2Complaints() 
	{
		return uploadRe2Complaints;
	}

	public String getUploadRe1Complaints() 
	{
		return uploadRe1Complaints;
	}

	public String getUploadAttachmentComplaints() {
		return uploadAttachmentComplaints;
	}

	public String getTotal() 
	{
		return Total;
	}

	public String getSafety()
    {
		return safety;
	}

	public String getLabelling() 
	{
		return Labelling;
	}

	public String getBoxes() 
	{
		return boxes;
	}


	public void setBoxes(String boxes) {
		this.boxes = boxes;
	}


	public String getMisfire() 
	{
		return misfire;
	}
	

	public String getStrengthPerformance()
	{
		return strengthPerformance;
	}

	public String getPhysicalCondition()
	{
		return physicalCondition;
	}

	public String getComplaintDetailFrom()
	{
		return complaintDetailFrom;
	}

	public String getCustomerComplaintFrom()
	{
		return customerComplaintFrom;
	}

	public String getRemarksFromTechnicalServices() 
	{
		return remarksFromTechnicalServices;
	}

	public String getRemarksfromPlant()
	{
		return remarksfromPlant;
	}

	public String getBatchNoDOM() 
	{
		return batchNoDOM;
	}

	public String getDateofReceiptofcomplaint()
	{
		return dateofReceiptofcomplaint;
	}

	public String getCustomersName()
	{
		return customersName;
	}

	public String getProduct() 
	{
		return product;
	}

	public String getReportIDN0()
	{
		return reportIDN0;
	}

	public String getSlNo() {
		return slNo;
	}

	public String getNatureofComplaint()
	{
		return natureofComplaint;
	}

	public String getLoginSuccess() {
		return loginSuccess;
	}

	public String getUserAlreadyAvailable() {
		return userAlreadyAvailable;
	}

	public String getUploadAttachment() {
		return uploadAttachment;
	}

	public String getError() {
		return error;
	}

	public String getUpdateSucess() {
		return updateSucess;
	}

	public String getDeleteSucess() {
		return deleteSucess;
	}

	public String getDeleteNotSucess() {
		return deleteNotSucess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getFieTypeError() {
		return fieTypeError;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public String getFieldIsEmpty() {
		return fieldIsEmpty;
	}

	public String getAttachmentNotSelected() {
		return attachmentNotSelected;
	}

	public String getDepartmentsAlreadyAvailable() {
		return departmentsAlreadyAvailable;
	}

	public String getDepartmentNotFound() {
		return departmentNotFound;
	}

}
