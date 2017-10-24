package com.websystique.springmvc.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.User;



@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		User user = getByKey(id);
		return user;
	}

	public User findBySSO(String sso) {
		System.out.println("SSO : "+sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		crit.add(Restrictions.eq("isDeleted", 1));
		User user = (User)crit.uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("isDeleted", 1));
		List<User> users = (List<User>) criteria.list();
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers(int id){
		Query query = getSession().createQuery("from User u where u.registerUser.id = :registerUser and u.isDeleted= :del order by u.firstName");
		query.setInteger("registerUser", id);
		query.setInteger("del", 1);
		return (List<User>) query.list();
	}
	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		delete(user);
	}

}
