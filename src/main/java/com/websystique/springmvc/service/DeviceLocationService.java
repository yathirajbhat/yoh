package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.DeviceLocation;

public interface DeviceLocationService {

	public List<DeviceLocation> getLocationByCity(String city);
}
