package com.websystique.springmvc.model;

import java.util.List;
import java.util.Map;

public class FullSlots {
	
	private Integer deviceId; 
	private String device;
	private List<String> date;
	private Map<Integer,List<String>> dateMap;
	private Map<Integer,String> deviceNameMap;
	
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getDevice() {
		
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public List<String> getDate() {
		return date;
	}
	public void setDate(List<String> date) {
		this.date = date;
	}
	public Map<Integer, List<String>> getDateMap() {
		return dateMap;
	}
	public void setDateMap(Map<Integer, List<String>> dateMap) {
		this.dateMap = dateMap;
	}
	public Map<Integer, String> getDeviceNameMap() {
		return deviceNameMap;
	}
	public void setDeviceNameMap(Map<Integer, String> deiceNameMap) {
		this.deviceNameMap = deiceNameMap;
	}
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		result = prime * result + deviceId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FullSlots other = (FullSlots) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		if (deviceId != other.deviceId)
			return false;
		return true;
	}
	
	*/
}
