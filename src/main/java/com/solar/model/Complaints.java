package com.solar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Complaints {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String customerId;
	private String customerName;
	private Date date;
	private String nameOfOrganization;
	private String nameOfMine;
	private String categoryOfComplaint;
	private String[] nameOfProduct;
	private String desciptionOfProduct;
	private String nameOfContactPersonCustomer;
	private String mobileNo;

	private String batchNo;
	private String caseNo;
	private String dateOfManufacturing;
	private String natureOfComplaint;
	private String type;
	private String remark;
	private String invoiceNo;
	private String uploadAttachment;
	private String uploadRe1;
	private String uploadRe2;

	private String approvalStageDepartment;
	private String approvalStageNotes;
	private String approvalStageForwardTo;
	private String approvalStageStatus;

	private String nameOfEngineer;
	private String nameOfContactPersonEngineer;
	private String conclusion;
	private String correctionActivity;
	private String clouser;
	private String recipient;
	private String uploadAttachment1;
	private String uploadAttachment2;
	private String uploadAttachment3;
	private String uploadAttachment4;

	private String companyId;
	private String companyName;
	private String plantId;
	private String plantName;
	private String reportDocumentNo;
	private String dateOfRecieptOfComplaint;
	private String natureComplaint;
	private String qtyDispatched;
	private String dateOfDispatch;
	private String qtyDefective;
	private String complaintInvestigationFindingRemark;
	private String correctiveActionIfAny;
	private String verificationOfEffectivenessOfCorrectionActions;
	private String complaintValidatedBy;
	private String complaintAttendedByIfAny;
	private String complaintJustifiedNotJustified;
	private String complaintReportAttachment;
	private String inChargeSignature;
	private String hodSignature;

	private String clouserRemark1;
	private String clouserRemark2;
	private String clouserRemark3;
	private String clouserRemark4;
	private String clouserAttachment;

	private Date dateOfCreate;
	private Date dateOfLevel1;// for manager approve and assign department
	private Date dateOfLevel2;// for department
	private Date dateOfLevel3;// for manger approve and assign sell-person
	private Date dateOfClosed;

	private String nameOfSalesPerson;
	private String nameOfLevel1;// for manager approve and assign department
	private String nameOfLevel2;// for department
	private String nameOfLevel3;// for manger approve and assign sell-person

	private String complaintStatus;

//	manger assign sell-person 
	private Integer approvalStageSellPersonId;
	private String approvalStageNotesSellPerson;
	private String approvalStageForwardToSellPerson;
	private String approvalStageStatusSellPerson;

//	fild report
	private Date dateOfLevel4;// for sell-person
	private String nameOfLevel4;// for sell-person
	private String nameOfClosed;

	@Transient
	private Date fromDate;
	@Transient
	private Date toDate;
	
	private Date createdDate;
	private Integer createdBy;
	private Date lastModifiedDate;
	private Integer lastModifiedBy;
	
	
	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Integer getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Integer lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Integer getApprovalStageSellPersonId() {
		return approvalStageSellPersonId;
	}

	public Complaints() {
		super();
	}

	public Complaints(int id,String customerId,
			String customerName, Date date, String nameOfOrganization,
			String nameOfMine, String categoryOfComplaint, String[] nameOfProduct, String desciptionOfProduct,
			String nameOfContactPersonCustomer, String mobileNo, String batchNo, String caseNo,
			String dateOfManufacturing, String natureOfComplaint, String type, String remark, String invoiceNo,
			String uploadAttachment, String uploadRe1, String uploadRe2, String approvalStageDepartment,
			String approvalStageNotes, String approvalStageForwardTo, String approvalStageStatus, String nameOfEngineer,
			String nameOfContactPersonEngineer, String conclusion, String correctionActivity, String clouser,
			String recipient, String uploadAttachment1, String uploadAttachment2, String uploadAttachment3,
			String uploadAttachment4, String companyId, String companyName, String plantId, String plantName,
			String reportDocumentNo, String dateOfRecieptOfComplaint, String natureComplaint, String qtyDispatched,
			String dateOfDispatch, String qtyDefective, String complaintInvestigationFindingRemark,
			String correctiveActionIfAny, String verificationOfEffectivenessOfCorrectionActions,
			String complaintValidatedBy, String complaintAttendedByIfAny, String complaintJustifiedNotJustified,
			String complaintReportAttachment, String inChargeSignature, String hodSignature, String clouserRemark1,
			String clouserRemark2, String clouserRemark3, String clouserRemark4, String clouserAttachment,
			Date dateOfCreate, Date dateOfLevel1, Date dateOfLevel2, Date dateOfLevel3, Date dateOfClosed,
			String nameOfSalesPerson, String nameOfLevel1, String nameOfLevel2, String nameOfLevel3,
			String complaintStatus, Integer approvalStageSellPersonId, String approvalStageNotesSellPerson,
			String approvalStageForwardToSellPerson, String approvalStageStatusSellPerson, Date dateOfLevel4,
			String nameOfLevel4, String nameOfClosed, Date fromDate, Date toDate) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customerName = customerName;
		this.date = date;
		this.nameOfOrganization = nameOfOrganization;
		this.nameOfMine = nameOfMine;
		this.categoryOfComplaint = categoryOfComplaint;
		this.nameOfProduct = nameOfProduct;
		this.desciptionOfProduct = desciptionOfProduct;
		this.nameOfContactPersonCustomer = nameOfContactPersonCustomer;
		this.mobileNo = mobileNo;
		this.batchNo = batchNo;
		this.caseNo = caseNo;
		this.dateOfManufacturing = dateOfManufacturing;
		this.natureOfComplaint = natureOfComplaint;
		this.type = type;
		this.remark = remark;
		this.invoiceNo = invoiceNo;
		this.uploadAttachment = uploadAttachment;
		this.uploadRe1 = uploadRe1;
		this.uploadRe2 = uploadRe2;
		this.approvalStageDepartment = approvalStageDepartment;
		this.approvalStageNotes = approvalStageNotes;
		this.approvalStageForwardTo = approvalStageForwardTo;
		this.approvalStageStatus = approvalStageStatus;
		this.nameOfEngineer = nameOfEngineer;
		this.nameOfContactPersonEngineer = nameOfContactPersonEngineer;
		this.conclusion = conclusion;
		this.correctionActivity = correctionActivity;
		this.clouser = clouser;
		this.recipient = recipient;
		this.uploadAttachment1 = uploadAttachment1;
		this.uploadAttachment2 = uploadAttachment2;
		this.uploadAttachment3 = uploadAttachment3;
		this.uploadAttachment4 = uploadAttachment4;
		this.companyId = companyId;
		this.companyName = companyName;
		this.plantId = plantId;
		this.plantName = plantName;
		this.reportDocumentNo = reportDocumentNo;
		this.dateOfRecieptOfComplaint = dateOfRecieptOfComplaint;
		this.natureComplaint = natureComplaint;
		this.qtyDispatched = qtyDispatched;
		this.dateOfDispatch = dateOfDispatch;
		this.qtyDefective = qtyDefective;
		this.complaintInvestigationFindingRemark = complaintInvestigationFindingRemark;
		this.correctiveActionIfAny = correctiveActionIfAny;
		this.verificationOfEffectivenessOfCorrectionActions = verificationOfEffectivenessOfCorrectionActions;
		this.complaintValidatedBy = complaintValidatedBy;
		this.complaintAttendedByIfAny = complaintAttendedByIfAny;
		this.complaintJustifiedNotJustified = complaintJustifiedNotJustified;
		this.complaintReportAttachment = complaintReportAttachment;
		this.inChargeSignature = inChargeSignature;
		this.hodSignature = hodSignature;
		this.clouserRemark1 = clouserRemark1;
		this.clouserRemark2 = clouserRemark2;
		this.clouserRemark3 = clouserRemark3;
		this.clouserRemark4 = clouserRemark4;
		this.clouserAttachment = clouserAttachment;
		this.dateOfCreate = dateOfCreate;
		this.dateOfLevel1 = dateOfLevel1;
		this.dateOfLevel2 = dateOfLevel2;
		this.dateOfLevel3 = dateOfLevel3;
		this.dateOfClosed = dateOfClosed;
		this.nameOfSalesPerson = nameOfSalesPerson;
		this.nameOfLevel1 = nameOfLevel1;
		this.nameOfLevel2 = nameOfLevel2;
		this.nameOfLevel3 = nameOfLevel3;
		this.complaintStatus = complaintStatus;
		this.approvalStageSellPersonId = approvalStageSellPersonId;
		this.approvalStageNotesSellPerson = approvalStageNotesSellPerson;
		this.approvalStageForwardToSellPerson = approvalStageForwardToSellPerson;
		this.approvalStageStatusSellPerson = approvalStageStatusSellPerson;
		this.dateOfLevel4 = dateOfLevel4;
		this.nameOfLevel4 = nameOfLevel4;
		this.nameOfClosed = nameOfClosed;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNameOfOrganization() {
		return nameOfOrganization;
	}

	public void setNameOfOrganization(String nameOfOrganization) {
		this.nameOfOrganization = nameOfOrganization;
	}

	public String getNameOfMine() {
		return nameOfMine;
	}

	public void setNameOfMine(String nameOfMine) {
		this.nameOfMine = nameOfMine;
	}

	public String getCategoryOfComplaint() {
		return categoryOfComplaint;
	}

	public void setCategoryOfComplaint(String categoryOfComplaint) {
		this.categoryOfComplaint = categoryOfComplaint;
	}

	public String[] getNameOfProduct() {
		return nameOfProduct;
	}

	public void setNameOfProduct(String[] nameOfProduct) {
		this.nameOfProduct = nameOfProduct;
	}

	public String getDesciptionOfProduct() {
		return desciptionOfProduct;
	}

	public void setDesciptionOfProduct(String desciptionOfProduct) {
		this.desciptionOfProduct = desciptionOfProduct;
	}

	public String getNameOfContactPersonCustomer() {
		return nameOfContactPersonCustomer;
	}

	public void setNameOfContactPersonCustomer(String nameOfContactPersonCustomer) {
		this.nameOfContactPersonCustomer = nameOfContactPersonCustomer;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getDateOfManufacturing() {
		return dateOfManufacturing;
	}

	public void setDateOfManufacturing(String dateOfManufacturing) {
		this.dateOfManufacturing = dateOfManufacturing;
	}

	public String getNatureOfComplaint() {
		return natureOfComplaint;
	}

	public void setNatureOfComplaint(String natureOfComplaint) {
		this.natureOfComplaint = natureOfComplaint;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getUploadAttachment() {
		return uploadAttachment;
	}

	public void setUploadAttachment(String uploadAttachment) {
		this.uploadAttachment = uploadAttachment;
	}

	public String getUploadRe1() {
		return uploadRe1;
	}

	public void setUploadRe1(String uploadRe1) {
		this.uploadRe1 = uploadRe1;
	}

	public String getUploadRe2() {
		return uploadRe2;
	}

	public void setUploadRe2(String uploadRe2) {
		this.uploadRe2 = uploadRe2;
	}

	public String getApprovalStageDepartment() {
		return approvalStageDepartment;
	}

	public void setApprovalStageDepartment(String approvalStageDepartment) {
		this.approvalStageDepartment = approvalStageDepartment;
	}

	public String getApprovalStageNotes() {
		return approvalStageNotes;
	}

	public void setApprovalStageNotes(String approvalStageNotes) {
		this.approvalStageNotes = approvalStageNotes;
	}

	public String getApprovalStageForwardTo() {
		return approvalStageForwardTo;
	}

	public void setApprovalStageForwardTo(String approvalStageForwardTo) {
		this.approvalStageForwardTo = approvalStageForwardTo;
	}

	public String getApprovalStageStatus() {
		return approvalStageStatus;
	}

	public void setApprovalStageStatus(String approvalStageStatus) {
		this.approvalStageStatus = approvalStageStatus;
	}

	public String getNameOfEngineer() {
		return nameOfEngineer;
	}

	public void setNameOfEngineer(String nameOfEngineer) {
		this.nameOfEngineer = nameOfEngineer;
	}

	public String getNameOfContactPersonEngineer() {
		return nameOfContactPersonEngineer;
	}

	public void setNameOfContactPersonEngineer(String nameOfContactPersonEngineer) {
		this.nameOfContactPersonEngineer = nameOfContactPersonEngineer;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getCorrectionActivity() {
		return correctionActivity;
	}

	public void setCorrectionActivity(String correctionActivity) {
		this.correctionActivity = correctionActivity;
	}

	public String getClouser() {
		return clouser;
	}

	public void setClouser(String clouser) {
		this.clouser = clouser;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getUploadAttachment1() {
		return uploadAttachment1;
	}

	public void setUploadAttachment1(String uploadAttachment1) {
		this.uploadAttachment1 = uploadAttachment1;
	}

	public String getUploadAttachment2() {
		return uploadAttachment2;
	}

	public void setUploadAttachment2(String uploadAttachment2) {
		this.uploadAttachment2 = uploadAttachment2;
	}

	public String getUploadAttachment3() {
		return uploadAttachment3;
	}

	public void setUploadAttachment3(String uploadAttachment3) {
		this.uploadAttachment3 = uploadAttachment3;
	}

	public String getUploadAttachment4() {
		return uploadAttachment4;
	}

	public void setUploadAttachment4(String uploadAttachment4) {
		this.uploadAttachment4 = uploadAttachment4;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getReportDocumentNo() {
		return reportDocumentNo;
	}

	public void setReportDocumentNo(String reportDocumentNo) {
		this.reportDocumentNo = reportDocumentNo;
	}

	public String getDateOfRecieptOfComplaint() {
		return dateOfRecieptOfComplaint;
	}

	public void setDateOfRecieptOfComplaint(String dateOfRecieptOfComplaint) {
		this.dateOfRecieptOfComplaint = dateOfRecieptOfComplaint;
	}

	public String getNatureComplaint() {
		return natureComplaint;
	}

	public void setNatureComplaint(String natureComplaint) {
		this.natureComplaint = natureComplaint;
	}

	public String getQtyDispatched() {
		return qtyDispatched;
	}

	public void setQtyDispatched(String qtyDispatched) {
		this.qtyDispatched = qtyDispatched;
	}

	public String getDateOfDispatch() {
		return dateOfDispatch;
	}

	public void setDateOfDispatch(String dateOfDispatch) {
		this.dateOfDispatch = dateOfDispatch;
	}

	public String getQtyDefective() {
		return qtyDefective;
	}

	public void setQtyDefective(String qtyDefective) {
		this.qtyDefective = qtyDefective;
	}

	public String getComplaintInvestigationFindingRemark() {
		return complaintInvestigationFindingRemark;
	}

	public void setComplaintInvestigationFindingRemark(String complaintInvestigationFindingRemark) {
		this.complaintInvestigationFindingRemark = complaintInvestigationFindingRemark;
	}

	public String getCorrectiveActionIfAny() {
		return correctiveActionIfAny;
	}

	public void setCorrectiveActionIfAny(String correctiveActionIfAny) {
		this.correctiveActionIfAny = correctiveActionIfAny;
	}

	public String getVerificationOfEffectivenessOfCorrectionActions() {
		return verificationOfEffectivenessOfCorrectionActions;
	}

	public void setVerificationOfEffectivenessOfCorrectionActions(
			String verificationOfEffectivenessOfCorrectionActions) {
		this.verificationOfEffectivenessOfCorrectionActions = verificationOfEffectivenessOfCorrectionActions;
	}

	public String getComplaintValidatedBy() {
		return complaintValidatedBy;
	}

	public void setComplaintValidatedBy(String complaintValidatedBy) {
		this.complaintValidatedBy = complaintValidatedBy;
	}

	public String getComplaintAttendedByIfAny() {
		return complaintAttendedByIfAny;
	}

	public void setComplaintAttendedByIfAny(String complaintAttendedByIfAny) {
		this.complaintAttendedByIfAny = complaintAttendedByIfAny;
	}

	public String getComplaintJustifiedNotJustified() {
		return complaintJustifiedNotJustified;
	}

	public void setComplaintJustifiedNotJustified(String complaintJustifiedNotJustified) {
		this.complaintJustifiedNotJustified = complaintJustifiedNotJustified;
	}

	public String getComplaintReportAttachment() {
		return complaintReportAttachment;
	}

	public void setComplaintReportAttachment(String complaintReportAttachment) {
		this.complaintReportAttachment = complaintReportAttachment;
	}

	public String getInChargeSignature() {
		return inChargeSignature;
	}

	public void setInChargeSignature(String inChargeSignature) {
		this.inChargeSignature = inChargeSignature;
	}

	public String getHodSignature() {
		return hodSignature;
	}

	public void setHodSignature(String hodSignature) {
		this.hodSignature = hodSignature;
	}

	public String getClouserRemark1() {
		return clouserRemark1;
	}

	public void setClouserRemark1(String clouserRemark1) {
		this.clouserRemark1 = clouserRemark1;
	}

	public String getClouserRemark2() {
		return clouserRemark2;
	}

	public void setClouserRemark2(String clouserRemark2) {
		this.clouserRemark2 = clouserRemark2;
	}

	public String getClouserRemark3() {
		return clouserRemark3;
	}

	public void setClouserRemark3(String clouserRemark3) {
		this.clouserRemark3 = clouserRemark3;
	}

	public String getClouserRemark4() {
		return clouserRemark4;
	}

	public void setClouserRemark4(String clouserRemark4) {
		this.clouserRemark4 = clouserRemark4;
	}

	public String getClouserAttachment() {
		return clouserAttachment;
	}

	public void setClouserAttachment(String clouserAttachment) {
		this.clouserAttachment = clouserAttachment;
	}

	public Date getDateOfCreate() {
		return dateOfCreate;
	}

	public void setDateOfCreate(Date dateOfCreate) {
		this.dateOfCreate = dateOfCreate;
	}

	public Date getDateOfLevel1() {
		return dateOfLevel1;
	}

	public void setDateOfLevel1(Date dateOfLevel1) {
		this.dateOfLevel1 = dateOfLevel1;
	}

	public Date getDateOfLevel2() {
		return dateOfLevel2;
	}

	public void setDateOfLevel2(Date dateOfLevel2) {
		this.dateOfLevel2 = dateOfLevel2;
	}

	public Date getDateOfLevel3() {
		return dateOfLevel3;
	}

	public void setDateOfLevel3(Date dateOfLevel3) {
		this.dateOfLevel3 = dateOfLevel3;
	}

	public Date getDateOfClosed() {
		return dateOfClosed;
	}

	public void setDateOfClosed(Date dateOfClosed) {
		this.dateOfClosed = dateOfClosed;
	}

	public String getNameOfSalesPerson() {
		return nameOfSalesPerson;
	}

	public void setNameOfSalesPerson(String nameOfSalesPerson) {
		this.nameOfSalesPerson = nameOfSalesPerson;
	}

	public String getNameOfLevel1() {
		return nameOfLevel1;
	}

	public void setNameOfLevel1(String nameOfLevel1) {
		this.nameOfLevel1 = nameOfLevel1;
	}

	public String getNameOfLevel2() {
		return nameOfLevel2;
	}

	public void setNameOfLevel2(String nameOfLevel2) {
		this.nameOfLevel2 = nameOfLevel2;
	}

	public String getNameOfLevel3() {
		return nameOfLevel3;
	}

	public void setNameOfLevel3(String nameOfLevel3) {
		this.nameOfLevel3 = nameOfLevel3;
	}

	public String getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}

	public Integer getApprovalStageSellPersonID() {
		return approvalStageSellPersonId;
	}

	public void setApprovalStageSellPersonId(Integer approvalStageSellPersonId) {
		this.approvalStageSellPersonId = approvalStageSellPersonId;
	}

	public String getApprovalStageNotesSellPerson() {
		return approvalStageNotesSellPerson;
	}

	public void setApprovalStageNotesSellPerson(String approvalStageNotesSellPerson) {
		this.approvalStageNotesSellPerson = approvalStageNotesSellPerson;
	}

	public String getApprovalStageForwardToSellPerson() {
		return approvalStageForwardToSellPerson;
	}

	public void setApprovalStageForwardToSellPerson(String approvalStageForwardToSellPerson) {
		this.approvalStageForwardToSellPerson = approvalStageForwardToSellPerson;
	}

	public String getApprovalStageStatusSellPerson() {
		return approvalStageStatusSellPerson;
	}

	public void setApprovalStageStatusSellPerson(String approvalStageStatusSellPerson) {
		this.approvalStageStatusSellPerson = approvalStageStatusSellPerson;
	}

	public Date getDateOfLevel4() {
		return dateOfLevel4;
	}

	public void setDateOfLevel4(Date dateOfLevel4) {
		this.dateOfLevel4 = dateOfLevel4;
	}

	public String getNameOfLevel4() {
		return nameOfLevel4;
	}

	public void setNameOfLevel4(String nameOfLevel4) {
		this.nameOfLevel4 = nameOfLevel4;
	}

	public String getNameOfClosed() {
		return nameOfClosed;
	}

	public void setNameOfClosed(String nameOfClosed) {
		this.nameOfClosed = nameOfClosed;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
