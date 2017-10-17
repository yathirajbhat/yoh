package com.websystique.springmvc.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.DeviceLocationDao;
import com.websystique.springmvc.model.DeviceLocation;
import com.websystique.springmvc.service.DeviceLocationService;

@Service("deviceLocationServiceImpl")
@Transactional
public class DeviceLocationServiceImpl implements DeviceLocationService {

	@Autowired DeviceLocationDao deviceLocationDao;
	
	public List<DeviceLocation> getLocationByCity(String city){
		return deviceLocationDao.getLocationByCity(city);
	}
}
