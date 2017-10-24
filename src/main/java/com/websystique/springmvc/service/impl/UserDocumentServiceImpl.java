package com.websystique.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.UserDocumentDao;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserDocument;
import com.websystique.springmvc.service.UserDocumentService;

@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService{

	@Autowired
	UserDocumentDao dao;

	public UserDocument findById(int id) {
		return dao.findById(id);
	}

	public List<UserDocument> findAll() {
		return dao.findAll();
	}

	public List<UserDocument> findAllByUserId(int userId) {
		return dao.findAllByUserId(userId);
	}
	
	public void saveDocument(UserDocument document){
		dao.save(document);
	}

	public void deleteById(int id){
		dao.deleteById(id);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	@Override
	public void updateDocument(int id) {
			UserDocument entity = dao.findById(id);
			if(entity!=null){
				entity.setIsDeleted(id);
		}
		
	}

}
