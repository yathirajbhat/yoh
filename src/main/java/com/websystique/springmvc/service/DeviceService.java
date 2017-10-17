package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.model.Device;
import com.websystique.springmvc.model.DeviceCategory;

public interface DeviceService {

	public List<Device> getDevicesByLocation(int deviceLocationId);
	
	public List<Device> getDevicesByLocation(List<Integer> deviceLocationId);
	
	public Device findDeviceById(int id);

	List<Device> getPrice(ArrayList<Integer> deviceCategory);
}
