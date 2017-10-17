package com.websystique.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICES")
public class Device {

	@Id
	@GeneratedValue
	@Column(name = "DEVICE_ID")
	private int id;
	
	@Column(name = "DEVICE_NAME")
	private String deviceName;
	
	@Column(name = "POSITION")
	private String position;
	
	@Column(name = "DEVICE_IMAGE_LOCATION")
	private String deviceImageLocation;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "DEVICE_LOCATION")
	private DeviceLocation deviceLocation;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "DEVICE_CATEGORY_ID")
	private DeviceCategory deviceCategory;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDeviceImageLocation() {
		return deviceImageLocation;
	}

	public void setDeviceImageLocation(String deviceImageLocation) {
		this.deviceImageLocation = deviceImageLocation;
	}

	public DeviceLocation getDeviceLocation() {
		return deviceLocation;
	}

	public void setDeviceLocation(DeviceLocation deviceLocation) {
		this.deviceLocation = deviceLocation;
	}

	public DeviceCategory getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(DeviceCategory deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", deviceName=" + deviceName + ", position=" + position + ", deviceImageLocation="
				+ deviceImageLocation + ", deviceLocation=" + deviceLocation + ", deviceCategory=" + deviceCategory
				+ "]";
	}
	
}
