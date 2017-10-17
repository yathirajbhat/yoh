package com.websystique.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.model.Device;
import com.websystique.springmvc.model.DeviceCategory;

public interface DevicesDao {

	public List<Device> getDevicesByLocation(int deviceLocationId);

	public Device findById(int id);

	public List<Device> getPrice(ArrayList<Integer> deviceCategory);
	
	public List<Device> getDevicesByLocation(List<Integer> deviceLocationId);
}
