package com.lenve.httpurlconnection;

public class ResutBean {

	private String status;
	private String content;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ResutBean(String status, String content) {
		this.status = status;
		this.content = content;
	}

	public ResutBean() {
	}

}
