package com.websystique.springmvc.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.dao.PlayingPriceDao;
import com.websystique.springmvc.model.DeviceCategory;
import com.websystique.springmvc.model.PlayingPrice;

@Repository("playingPriceDao")
public class PlayingPriceDaoImpl extends AbstractDao<Integer, PlayingPrice> implements PlayingPriceDao {

	@Override
	public PlayingPrice getPriceForHour(int hour,DeviceCategory category) {
		System.out.println("ABSHHDCDCJHB");
		Query query=getSession().createQuery("from PlayingPrice pp join pp.deviceCategory where :hour >= pp.playingStartHours and :hour <= pp.playingEndHours and pp.deviceCategory.id= :category");
		query.setParameter("hour", hour);
		query.setParameter("category", category.getId());
		System.out.println(query.uniqueResult().toString());
		return (PlayingPrice)query.uniqueResult();
		/*Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.add(Restrictions.between("hour", "playingStartHours", "playingEndHours")).createCriteria("deviceCategory");
		userCriteria.add(Restrictions.eq("deviceCategory", category));
		return (PlayingPrice)crit.uniqueResult();*/
	}

}
