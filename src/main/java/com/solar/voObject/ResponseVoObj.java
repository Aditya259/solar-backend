package com.solar.voObject;

import java.util.List;

public class ResponseVoObj {

	private boolean status;
	private String error;
	private Object data;
	private List<?> attachments;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<?> attachments) {
		this.attachments = attachments;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
