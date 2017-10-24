package com.websystique.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = dao.findBySSO(sso);
		return user;
	}

	public void saveUser(User user) {
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user,int edit) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserDocuments(user.getUserDocuments());
			entity.setCompany(user.getCompany());
			entity.setPhone(user.getPhone());
			entity.setIsDeleted(edit);
		}
	}

	
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public List<User> findAllUsers(int id) {
		return dao.findAllUsers(id);
	}
	
}
