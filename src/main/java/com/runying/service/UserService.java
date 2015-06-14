package com.runying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.UserDao;
import com.runying.po.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDaoProxy;
	
	public List<User> getAllUser(int pageNumber, int countPerPage) {
		return userDaoProxy.findAll(pageNumber, countPerPage);
	}
}
