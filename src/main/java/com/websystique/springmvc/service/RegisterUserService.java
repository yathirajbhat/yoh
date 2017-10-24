package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.RegisterUser;


public interface RegisterUserService {
	
	RegisterUser findById(int id);
	
	RegisterUser findBySSO(String sso);
	
	void saveUser(RegisterUser user);
	
	void updateUser(RegisterUser user,int edit);
	
	void deleteUserBySSO(String sso);

	List<RegisterUser> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);

}