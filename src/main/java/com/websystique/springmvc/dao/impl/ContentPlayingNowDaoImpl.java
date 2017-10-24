package com.websystique.springmvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.dao.ContentPlayingNowDao;
import com.websystique.springmvc.model.ContentPlayingNow;

@Repository("contentPlayingNowDao")
public class ContentPlayingNowDaoImpl extends AbstractDao<Integer, ContentPlayingNow> implements ContentPlayingNowDao{

	@SuppressWarnings("unchecked")
	public List<ContentPlayingNow> findByDeviceId(int deviceId){
		Criteria crit = createEntityCriteria();
		Criteria deviceCrit = crit.add(Restrictions.ne("isDeleted",1)).createCriteria("device");
		deviceCrit.add(Restrictions.eq("id", deviceId));
		//Criteria userDocCrit = crit.createCriteria("userDocument");
		//userDocCrit.setProjection(Projections.groupProperty("playGroup"));
		crit.addOrder(Order.asc("startTime"));
		return (List<ContentPlayingNow>)crit.list();
	}
	

	public void save(ContentPlayingNow contentPlayingNow) {
		persist(contentPlayingNow);
	}


	@Override
	public int getTotalSlotsInDay(String date, int deviceId) {
		Query query= getSession().createQuery("select count(*) from ContentPlayingNow where contnent_start_time like :date and device_id=:deviceId");
		 query.setString("date", date +"%");
		 query.setInteger("deviceId", deviceId);
		 Long l =(Long) query.uniqueResult();
		return l.intValue();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ContentPlayingNow> getContent(int userId) {
		Query query= getSession().createQuery("from ContentPlayingNow cpn where cpn.userDocument.user.id = :id");
		System.out.println(query.toString());
		 query.setInteger("id", userId);
		return (List<ContentPlayingNow>)query.list();
	}
}
