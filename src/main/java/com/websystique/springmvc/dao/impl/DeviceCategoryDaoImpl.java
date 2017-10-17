package com.websystique.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.dao.DeviceCategoryDao;
import com.websystique.springmvc.model.DeviceCategory;

@Repository("deviceCategoryDao")
public class DeviceCategoryDaoImpl extends AbstractDao<Integer, DeviceCategory> implements DeviceCategoryDao {

	@Override
	public DeviceCategory getDeviceCategoryByName(String category) {
		Query query= getSession().createQuery("from DeviceCategory where category= :category");
		 query.setString("category", category);
		return (DeviceCategory)query.uniqueResult();
	}

}
