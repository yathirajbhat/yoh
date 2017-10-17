package com.websystique.springmvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractUserDao;
import com.websystique.springmvc.dao.RegisterUserDao;
import com.websystique.springmvc.model.RegisterUser;



@Repository("registeredUserDao")
public class RegisterUserDaoImpl extends AbstractUserDao<Integer, RegisterUser> implements RegisterUserDao {

	static final Logger logger = LoggerFactory.getLogger(RegisterUserDaoImpl.class);
	
	public RegisterUser findById(int id) {
		RegisterUser user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public RegisterUser findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		RegisterUser user = (RegisterUser)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<RegisterUser> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<RegisterUser> users = (List<RegisterUser>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}

	public void save(RegisterUser user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		RegisterUser user = (RegisterUser)crit.uniqueResult();
		delete(user);
	}

}
