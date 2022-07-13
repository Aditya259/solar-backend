package com.solar.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Feedbacks {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String companyId;
	private String companyName;
	private String date;
	
	private String nameOfMine;
	private String contactPersonName;
	private String designation;
	private String department;
	private String signature;
	private String[] productUsed;
	
	private Integer productQuality1;
	private Integer productQuality2;
	private Integer delivery1;
	private Integer delivery2;
	private Integer delivery3;
	private Integer service1;
	private Integer service2;
	private Integer service3;
	private Integer behaviourOfOurStaff1;
	private Integer behaviourOfOurStaff2;
	private Integer behaviourOfOurStaff3;
	private Integer competitiveness1;
	private Integer competitiveness2;
	
	private String anyOtherComments1;
	private String anyOtherComments2;
	private String anyOtherComments3;
	private String anyOtherComments4;
	private String anyOtherComments5;
	private String uploadAttachment;
	private String uploadedBy;
	
	private Date dateOfCreate;
	private Date dateOfLevel1;
	
	private String nameOfSalesPerson;
	private String nameOfLevel1;
	
	private String feedBackStatus;
	
	private Integer sectorId;
	private String sectorName;
	
	private Integer totalPoints;
	private Integer percent;
	private String overallPerformance;
	
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

	public Feedbacks() {
		super();
	}

	public Feedbacks(int id, String companyId, String companyName, String date, String nameOfMine,
			String contactPersonName, String designation, String department, String signature, String[] productUsed,
			Integer productQuality1, Integer productQuality2, Integer delivery1, Integer delivery2, Integer delivery3,
			Integer service1, Integer service2, Integer service3, Integer behaviourOfOurStaff1,
			Integer behaviourOfOurStaff2, Integer behaviourOfOurStaff3, Integer competitiveness1,
			Integer competitiveness2, String anyOtherComments1, String anyOtherComments2, String anyOtherComments3,
			String anyOtherComments4, String anyOtherComments5, String uploadAttachment, String uploadedBy,
			Date dateOfCreate, Date dateOfLevel1, String nameOfSalesPerson, String nameOfLevel1, String feedBackStatus,
			Integer sectorId, String sectorName, Integer totalPoints, Integer percent, String overallPerformance,
			Date fromDate, Date toDate) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.companyName = companyName;
		this.date = date;
		this.nameOfMine = nameOfMine;
		this.contactPersonName = contactPersonName;
		this.designation = designation;
		this.department = department;
		this.signature = signature;
		this.productUsed = productUsed;
		this.productQuality1 = productQuality1;
		this.productQuality2 = productQuality2;
		this.delivery1 = delivery1;
		this.delivery2 = delivery2;
		this.delivery3 = delivery3;
		this.service1 = service1;
		this.service2 = service2;
		this.service3 = service3;
		this.behaviourOfOurStaff1 = behaviourOfOurStaff1;
		this.behaviourOfOurStaff2 = behaviourOfOurStaff2;
		this.behaviourOfOurStaff3 = behaviourOfOurStaff3;
		this.competitiveness1 = competitiveness1;
		this.competitiveness2 = competitiveness2;
		this.anyOtherComments1 = anyOtherComments1;
		this.anyOtherComments2 = anyOtherComments2;
		this.anyOtherComments3 = anyOtherComments3;
		this.anyOtherComments4 = anyOtherComments4;
		this.anyOtherComments5 = anyOtherComments5;
		this.uploadAttachment = uploadAttachment;
		this.uploadedBy = uploadedBy;
		this.dateOfCreate = dateOfCreate;
		this.dateOfLevel1 = dateOfLevel1;
		this.nameOfSalesPerson = nameOfSalesPerson;
		this.nameOfLevel1 = nameOfLevel1;
		this.feedBackStatus = feedBackStatus;
		this.sectorId = sectorId;
		this.sectorName = sectorName;
		this.totalPoints = totalPoints;
		this.percent = percent;
		this.overallPerformance = overallPerformance;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNameOfMine() {
		return nameOfMine;
	}

	public void setNameOfMine(String nameOfMine) {
		this.nameOfMine = nameOfMine;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String[] getProductUsed() {
		return productUsed;
	}

	public void setProductUsed(String[] productUsed) {
		this.productUsed = productUsed;
	}

	public Integer getProductQuality1() {
		return productQuality1;
	}

	public void setProductQuality1(Integer productQuality1) {
		this.productQuality1 = productQuality1;
	}

	public Integer getProductQuality2() {
		return productQuality2;
	}

	public void setProductQuality2(Integer productQuality2) {
		this.productQuality2 = productQuality2;
	}

	public Integer getDelivery1() {
		return delivery1;
	}

	public void setDelivery1(Integer delivery1) {
		this.delivery1 = delivery1;
	}

	public Integer getDelivery2() {
		return delivery2;
	}

	public void setDelivery2(Integer delivery2) {
		this.delivery2 = delivery2;
	}

	public Integer getDelivery3() {
		return delivery3;
	}

	public void setDelivery3(Integer delivery3) {
		this.delivery3 = delivery3;
	}

	public Integer getService1() {
		return service1;
	}

	public void setService1(Integer service1) {
		this.service1 = service1;
	}

	public Integer getService2() {
		return service2;
	}

	public void setService2(Integer service2) {
		this.service2 = service2;
	}

	public Integer getService3() {
		return service3;
	}

	public void setService3(Integer service3) {
		this.service3 = service3;
	}

	public Integer getBehaviourOfOurStaff1() {
		return behaviourOfOurStaff1;
	}

	public void setBehaviourOfOurStaff1(Integer behaviourOfOurStaff1) {
		this.behaviourOfOurStaff1 = behaviourOfOurStaff1;
	}

	public Integer getBehaviourOfOurStaff2() {
		return behaviourOfOurStaff2;
	}

	public void setBehaviourOfOurStaff2(Integer behaviourOfOurStaff2) {
		this.behaviourOfOurStaff2 = behaviourOfOurStaff2;
	}

	public Integer getBehaviourOfOurStaff3() {
		return behaviourOfOurStaff3;
	}

	public void setBehaviourOfOurStaff3(Integer behaviourOfOurStaff3) {
		this.behaviourOfOurStaff3 = behaviourOfOurStaff3;
	}

	public Integer getCompetitiveness1() {
		return competitiveness1;
	}

	public void setCompetitiveness1(Integer competitiveness1) {
		this.competitiveness1 = competitiveness1;
	}

	public Integer getCompetitiveness2() {
		return competitiveness2;
	}

	public void setCompetitiveness2(Integer competitiveness2) {
		this.competitiveness2 = competitiveness2;
	}

	public String getAnyOtherComments1() {
		return anyOtherComments1;
	}

	public void setAnyOtherComments1(String anyOtherComments1) {
		this.anyOtherComments1 = anyOtherComments1;
	}

	public String getAnyOtherComments2() {
		return anyOtherComments2;
	}

	public void setAnyOtherComments2(String anyOtherComments2) {
		this.anyOtherComments2 = anyOtherComments2;
	}

	public String getAnyOtherComments3() {
		return anyOtherComments3;
	}

	public void setAnyOtherComments3(String anyOtherComments3) {
		this.anyOtherComments3 = anyOtherComments3;
	}

	public String getAnyOtherComments4() {
		return anyOtherComments4;
	}

	public void setAnyOtherComments4(String anyOtherComments4) {
		this.anyOtherComments4 = anyOtherComments4;
	}

	public String getAnyOtherComments5() {
		return anyOtherComments5;
	}

	public void setAnyOtherComments5(String anyOtherComments5) {
		this.anyOtherComments5 = anyOtherComments5;
	}

	public String getUploadAttachment() {
		return uploadAttachment;
	}

	public void setUploadAttachment(String uploadAttachment) {
		this.uploadAttachment = uploadAttachment;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
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

	public String getFeedBackStatus() {
		return feedBackStatus;
	}

	public void setFeedBackStatus(String feedBackStatus) {
		this.feedBackStatus = feedBackStatus;
	}

	public Integer getSectorId() {
		return sectorId;
	}

	public void setSectorId(Integer sectorId) {
		this.sectorId = sectorId;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public String getOverallPerformance() {
		return overallPerformance;
	}

	public void setOverallPerformance(String overallPerformance) {
		this.overallPerformance = overallPerformance;
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

	@Override
	public String toString() {
		return "Feedbacks [id=" + id + ", companyID=" + companyId + ", companyName=" + companyName + ", date=" + date
				+ ", nameOfMine=" + nameOfMine + ", contactPersonName=" + contactPersonName + ", designation="
				+ designation + ", department=" + department + ", signature=" + signature + ", productUsed="
				+ Arrays.toString(productUsed) + ", productQuality1=" + productQuality1 + ", productQuality2="
				+ productQuality2 + ", delivery1=" + delivery1 + ", delivery2=" + delivery2 + ", delivery3=" + delivery3
				+ ", service1=" + service1 + ", service2=" + service2 + ", service3=" + service3
				+ ", behaviourOfOurStaff1=" + behaviourOfOurStaff1 + ", behaviourOfOurStaff2=" + behaviourOfOurStaff2
				+ ", behaviourOfOurStaff3=" + behaviourOfOurStaff3 + ", competitiveness1=" + competitiveness1
				+ ", competitiveness2=" + competitiveness2 + ", anyOtherComments1=" + anyOtherComments1
				+ ", anyOtherComments2=" + anyOtherComments2 + ", anyOtherComments3=" + anyOtherComments3
				+ ", anyOtherComments4=" + anyOtherComments4 + ", anyOtherComments5=" + anyOtherComments5
				+ ", upload_attachment=" + uploadAttachment + ", uploadedBy=" + uploadedBy + ", dateOfCreate="
				+ dateOfCreate + ", dateOfLevel1=" + dateOfLevel1 + ", nameOfSalesPerson=" + nameOfSalesPerson
				+ ", nameOfLevel1=" + nameOfLevel1 + ", feedBackStatus=" + feedBackStatus + ", sectorID=" + sectorId
				+ ", sectorName=" + sectorName + ", totalPoints=" + totalPoints + ", percent=" + percent
				+ ", overallPerformance=" + overallPerformance + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

	
	
}
