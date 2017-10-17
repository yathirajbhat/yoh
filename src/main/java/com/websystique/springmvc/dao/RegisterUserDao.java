package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.RegisterUser;


public interface RegisterUserDao {

	RegisterUser findById(int id);
	
	RegisterUser findBySSO(String sso);
	
	void save(RegisterUser user);
	
	void deleteBySSO(String sso);
	
	List<RegisterUser> findAllUsers();

}

