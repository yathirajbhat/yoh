package com.websystique.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.model.DeviceCategory;

public interface DeviceCategoryDao {

	public DeviceCategory getDeviceCategoryByName(String category);
	
}
