package com.runying.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.runying.po.User;
import com.runying.util.DaoUtil;
import com.runying.util.MD5Util;

public class UserDao extends DaoUtil{
	
	public <T> List<T> loginCheck(String username, String password) {
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("username", username);
		//数据库保存的密码为加密后的密码
		password = MD5Util.string2MD5(password);
		maps.put("password", password);
		//找出非删除的用户
		maps.put("status", 1);
		return this.findByColumns(maps);
	}
	
	public User findByUsername(String username) {
		Map<String, Object> cols = new HashMap<String, Object>();
		//找出非删除的用户
		cols.put("username", username);
		cols.put("status", 1);
		
		List<User> us = this.findByColumns(cols);
		if(us != null && us.size() !=0) {
			return us.get(0);
		} else {
			return null;
		}
	}

	public Long sizeWithStatus(int status) {
		return (long) this.findByColumn("status", status).size();
	}
	
	@Override
	protected String className() {
		// TODO Auto-generated method stub
		return "User";
	}
}
