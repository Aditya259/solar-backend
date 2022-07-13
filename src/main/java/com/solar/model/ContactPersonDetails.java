package com.solar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact_person_details")
public class ContactPersonDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "company")
	private String company;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "designation")
	private String designation;

	@Column(name = "email")
	private String email;

	@Column(name = "custome_details_id")
	private int customeDetails_Id;

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

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCustomeDetails_Id() {
		return customeDetails_Id;
	}

	public void setCustomeDetails_Id(int customeDetails_Id) {
		this.customeDetails_Id = customeDetails_Id;
	}

	@Override
	public String toString() {
		return "ContactPersonDetails [id=" + id + ", company=" + company + ", contactPerson=" + contactPerson
				+ ", designation=" + designation + ", email=" + email + "]";
	}

}
