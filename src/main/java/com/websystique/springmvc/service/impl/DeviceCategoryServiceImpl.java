package com.websystique.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.DeviceCategoryDao;
import com.websystique.springmvc.model.DeviceCategory;
import com.websystique.springmvc.service.DeviceCategoryService;

@Service("deviceCategoryService")
@Transactional
public class DeviceCategoryServiceImpl implements DeviceCategoryService {

	@Autowired
	DeviceCategoryDao deviceCategorydao;
	
	@Override
	public DeviceCategory getDeviceCategoryByName(String category) {
		return deviceCategorydao.getDeviceCategoryByName(category);
	}

}
