package com.websystique.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.DevicesDao;
import com.websystique.springmvc.model.Device;
import com.websystique.springmvc.service.DeviceService;

@Service("deviceService")
@Transactional
public class DeviceServiceImpl implements DeviceService{

	@Autowired DevicesDao devicesDao;
	
	public List<Device> getDevicesByLocation(int deviceLocationId){
		return devicesDao.getDevicesByLocation(deviceLocationId);
	}

	@Override
	public Device findDeviceById(int id) {
		return devicesDao.findById(id);
	}
	
	@Override
	public List<Device> getPrice(ArrayList<Integer> deviceCategory) {
		return devicesDao.getPrice(deviceCategory);
	}

	@Override
	public List<Device> getDevicesByLocation(List<Integer> deviceLocationId) {
		return devicesDao.getDevicesByLocation(deviceLocationId);
	}
	
}
