package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.DeviceLocation;

public interface DeviceLocationDao{

	public List<DeviceLocation> getLocationByCity(String city);
}
