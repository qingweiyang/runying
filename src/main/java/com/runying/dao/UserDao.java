package com.runying.dao;

import java.util.List;

import com.runying.po.User;
import com.runying.util.DaoUtil;

public class UserDao extends DaoUtil{
	
	public User findByUsername(String username) {
		List<User> us = this.findByColumn("username", username);
		if(us != null && us.size() !=0) {
			return us.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected String className() {
		// TODO Auto-generated method stub
		return "User";
	}
}
