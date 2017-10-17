package com.websystique.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.RegisterUserDao;
import com.websystique.springmvc.model.RegisterUser;
import com.websystique.springmvc.service.RegisterUserService;


@Service("registeredUserService")
@Transactional
public class RegisterUserServiceImpl implements RegisterUserService{

	@Autowired
	private RegisterUserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public RegisterUser findById(int id) {
		return dao.findById(id);
	}

	public RegisterUser findBySSO(String sso) {
		RegisterUser user = dao.findBySSO(sso);
		return user;
	}

	public void saveUser(RegisterUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(RegisterUser user) {
		RegisterUser entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<RegisterUser> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		RegisterUser user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
}
