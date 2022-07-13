package com.solar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sectors {

	@Id
	private int id;
	private String name;
	private String yearly_quterly;
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

	public Sectors() {
		super();
	}

	public Sectors(int id, String name, String yearly_quterly) {
		super();
		this.id = id;
		this.name = name;
		this.yearly_quterly = yearly_quterly;
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

	public String getYearly_quterly() {
		return yearly_quterly;
	}

	public void setYearly_quterly(String yearly_quterly) {
		this.yearly_quterly = yearly_quterly;
	}

	@Override
	public String toString() {
		return "Sectors [id=" + id + ", name=" + name + ", yearly_quterly=" + yearly_quterly + "]";
	}
	
	
}
