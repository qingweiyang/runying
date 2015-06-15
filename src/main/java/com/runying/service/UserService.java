package com.runying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.UserDao;
import com.runying.po.User;
import com.runying.util.Msg;
import com.runying.vo.TableVO;

@Service
public class UserService {
	@Autowired
	private UserDao userDaoProxy;
	
	public TableVO<User> getAllUser(int pageNumber, int countPerPage) {
		List<User> us = userDaoProxy.findAll(pageNumber, countPerPage);
		TableVO<User> tvo = new TableVO<User>();
		tvo.setRows(us);
		tvo.setCurrentPage(pageNumber);
		tvo.setCountPerPage(countPerPage);
		tvo.setPages((userDaoProxy.size()-1) / countPerPage + 1);
		return tvo;
	}
	
	public Msg editUserPrivilege(User u, User operator) {
		Msg msg = new Msg();
		
		User resDB = userDaoProxy.findByUsername(operator.getUsername());
		//检查操作员是否拥有 编辑用户权限 的权限
		if(1 != (resDB.getPrivilege() >> 3 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		User uDB = userDaoProxy.findByUsername(u.getUsername());
		//检查该用户是否存在
		if(uDB == null) {
			msg.setStatus(0);
			msg.setDescription("用户 " + u.getUsername() + " 不存在");
			return msg;
		}
		
		uDB.setPrivilege(u.getPrivilege());
		userDaoProxy.updat(uDB);
		
		msg.setStatus(1);
		return msg;
	}
}
