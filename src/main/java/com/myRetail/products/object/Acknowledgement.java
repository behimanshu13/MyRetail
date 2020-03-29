package com.myRetail.products.object;

public class Acknowledgement {
	private String updateStatus;
	private String updatedTime;
	
	public Acknowledgement() {
		super();
	}
	public Acknowledgement(int responseCode, String updateStatus, String updatedTime) {
		super();
		this.updateStatus = updateStatus;
		this.updatedTime = updatedTime;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	
}
