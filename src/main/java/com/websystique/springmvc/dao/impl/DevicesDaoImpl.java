package com.websystique.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.dao.DevicesDao;
import com.websystique.springmvc.model.Device;
import com.websystique.springmvc.model.DeviceCategory;

@Repository("devicesDao")
public class DevicesDaoImpl extends AbstractDao<Integer, Device> implements DevicesDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDevicesByLocation(int deviceLocationId) {
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("deviceLocation");
		userCriteria.add(Restrictions.eq("id", deviceLocationId));
		return (List<Device>)crit.list();
	}

	@Override
	public Device findById(int id) {
		return getByKey(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getPrice(ArrayList<Integer> device) {
		Query query= getSession().createQuery("from Device where id in :device");
		 query.setParameterList("device", device);
		return (List<Device>)query.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDevicesByLocation(List<Integer> deviceLocationId) {
		Query query= getSession().createQuery("from Device d INNER JOIN d.deviceLocation where d.deviceLocation.id in :deviceLocationId");
		 query.setParameterList("deviceLocationId", deviceLocationId);
		return (List<Device>)query.list();
	}
	
}
