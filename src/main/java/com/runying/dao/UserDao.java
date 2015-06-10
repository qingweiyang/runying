package com.runying.dao;

import java.util.List;

import com.runying.po.User;
import com.runying.util.DaoUtil;

public class UserDao extends DaoUtil{
	
	private final String className = "User";
	
	public User findByUsername(String username) {
		List<User> us = this.findByColumn(className, "username", username);
		if(us != null && us.size() !=0) {
			return us.get(0);
		} else {
			return null;
		}
	}
}
