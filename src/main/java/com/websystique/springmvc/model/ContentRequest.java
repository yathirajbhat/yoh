package com.websystique.springmvc.model;

import java.util.Date;
import java.util.List;

public class ContentRequest {

	private List<Integer> deviceId;
	private int contnetId;
	private Date startDate;
	private Date endDate;
	private double price;
	
	public List<Integer> getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(List<Integer> deviceId) {
		this.deviceId = deviceId;
	}
	public int getContnetId() {
		return contnetId;
	}
	public void setContnetId(int contnetId) {
		this.contnetId = contnetId;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
