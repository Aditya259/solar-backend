package com.solar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeedbacksQuarterlyTarget {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String year;
	private Integer institution;
	private Integer trade;
	private Integer export;
	private Integer wcl;
	private Integer mcl;
	private Integer ncl;
	private Integer sccl;
	private Integer bccl;
	private Integer ccl;
	private Integer ecl;
	private Integer secl;
	private Date createAt;
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

	public FeedbacksQuarterlyTarget() {
		super();
	}

	public FeedbacksQuarterlyTarget(int id, String year, Integer institution, Integer trade, Integer export,
			Integer wcl, Integer mcl, Integer ncl, Integer sccl, Integer bccl, Integer ccl, Integer ecl, Integer secl,
			Date createAt) {
		super();
		this.id = id;
		this.year = year;
		this.institution = institution;
		this.trade = trade;
		this.export = export;
		this.wcl = wcl;
		this.mcl = mcl;
		this.ncl = ncl;
		this.sccl = sccl;
		this.bccl = bccl;
		this.ccl = ccl;
		this.ecl = ecl;
		this.secl = secl;
		this.createAt = createAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getInstitution() {
		return institution;
	}

	public void setInstitution(Integer institution) {
		this.institution = institution;
	}

	public Integer getTrade() {
		return trade;
	}

	public void setTrade(Integer trade) {
		this.trade = trade;
	}

	public Integer getExport() {
		return export;
	}

	public void setExport(Integer export) {
		this.export = export;
	}

	public Integer getWcl() {
		return wcl;
	}

	public void setWcl(Integer wcl) {
		this.wcl = wcl;
	}

	public Integer getMcl() {
		return mcl;
	}

	public void setMcl(Integer mcl) {
		this.mcl = mcl;
	}

	public Integer getNcl() {
		return ncl;
	}

	public void setNcl(Integer ncl) {
		this.ncl = ncl;
	}

	public Integer getSccl() {
		return sccl;
	}

	public void setSccl(Integer sccl) {
		this.sccl = sccl;
	}

	public Integer getBccl() {
		return bccl;
	}

	public void setBccl(Integer bccl) {
		this.bccl = bccl;
	}

	public Integer getCcl() {
		return ccl;
	}

	public void setCcl(Integer ccl) {
		this.ccl = ccl;
	}

	public Integer getEcl() {
		return ecl;
	}

	public void setEcl(Integer ecl) {
		this.ecl = ecl;
	}

	public Integer getSecl() {
		return secl;
	}

	public void setSecl(Integer secl) {
		this.secl = secl;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Feedbacks_Quarterly_Target [id=" + id + ", year=" + year + ", institution=" + institution + ", trade="
				+ trade + ", export=" + export + ", wcl=" + wcl + ", mcl=" + mcl + ", ncl=" + ncl + ", sccl=" + sccl
				+ ", bccl=" + bccl + ", ccl=" + ccl + ", ecl=" + ecl + ", secl=" + secl + ", createAt=" + createAt
				+ "]";
	}
	
	
}
