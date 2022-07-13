package com.solar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "extra_details")
public class ExtraDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "log_date")
	private String logDate;

	@Column(name = "log_time")
	private String logTime;

	@Column(name = "subject")
	private String subject;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "description")
	private String description;

	@Column(name = "purpose_of_email")
	private String purposeOfEmail;

	@Transient
	@Column(name = "attachmet")
	private String attachmet;

	@Column(name = "mode_of_contact")
	private String modeOfContact;

	@Column(name = "custome_details_id")
	private int customeDetailsId;

	public int getCustomeDetailsId() {
		return customeDetailsId;
	}

	public void setCustomeDetailsId(int customeDetailsId) {
		this.customeDetailsId = customeDetailsId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPurposeOfEmail() {
		return purposeOfEmail;
	}

	public void setPurposeOfEmail(String purposeOfEmail) {
		this.purposeOfEmail = purposeOfEmail;
	}

	public String getAttachmet() {
		return attachmet;
	}

	public void setAttachmet(String attachmet) {
		this.attachmet = attachmet;
	}

	public String getModeOfContact() {
		return modeOfContact;
	}

	public void setModeOfContact(String modeOfContact) {
		this.modeOfContact = modeOfContact;
	}

	@Override
	public String toString() {
		return "ExtraDetails [id=" + id + ", logDate=" + logDate + ", logTime=" + logTime + ", subject=" + subject
				+ ", contactPerson=" + contactPerson + ", contactNumber=" + contactNumber + ", description="
				+ description + ", purposeOfEmail=" + purposeOfEmail + ", attachmet=" + attachmet + ", modeOfContact="
				+ modeOfContact + "]";
	}

}
