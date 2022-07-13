package com.solar.voObject;

public class DashboardVo {
	
	private long totalComplaints;
	private long totalFeedback;
	private long totalDepartments;
	private long totalAdmins;
	private boolean status;
	private String error;
	public long getTotalComplaints() {
		return totalComplaints;
	}
	public void setTotalComplaints(long totalComplaints) {
		this.totalComplaints = totalComplaints;
	}
	public long getTotalFeedback() {
		return totalFeedback;
	}
	public void setTotalFeedback(long totalFeedback) {
		this.totalFeedback = totalFeedback;
	}
	public long getTotalDepartments() {
		return totalDepartments;
	}
	public void setTotalDepartments(long totalDepartments) {
		this.totalDepartments = totalDepartments;
	}
	public long getTotalAdmins() {
		return totalAdmins;
	}
	public void setTotalAdmins(long totalAdmins) {
		this.totalAdmins = totalAdmins;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
