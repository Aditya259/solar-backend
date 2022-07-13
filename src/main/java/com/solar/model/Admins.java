package com.solar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Admins {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String user;
	private String pass;
	private String role;
	private String department;
	private Date createdDate;
	private Integer createdBy;
	private Date lastModifiedDate;
	private Integer lastModifiedBy;
	
	

	
	public Date getCreatedDate() {
		return createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public Integer getLastModifiedBy() {
		return lastModifiedBy;
	}

	@Transient
	private String status;

	public Admins() {
		super();
	}

	public Admins(int id, String name, String user, String pass, String role, String department, Date createAt,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.pass = pass;
		this.role = role;
		this.department = department;
		this.status = status;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setLastModifiedBy(Integer lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
