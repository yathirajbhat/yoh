package com.websystique.springmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.dao.DeviceLocationDao;
import com.websystique.springmvc.model.DeviceLocation;

@Repository("deviceLocationDao")
public class DeviceLocationDaoImpl extends AbstractDao<Integer, DeviceLocation> implements DeviceLocationDao{
	
	@SuppressWarnings("unchecked")
	public List<DeviceLocation> getLocationByCity(String city) {
		Query query=getSession().createQuery("from DeviceLocation where city in city");
		return query.list();
	}
}
