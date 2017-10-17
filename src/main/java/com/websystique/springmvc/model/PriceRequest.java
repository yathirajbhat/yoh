package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.Date;

public class PriceRequest {

	private ArrayList<Integer> deviceId;
	private Date startDate;
	private Date endDate;
	
	public ArrayList<Integer> getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(ArrayList<Integer> deviceId) {
		this.deviceId = deviceId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
