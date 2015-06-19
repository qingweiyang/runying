package com.runying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.UserDao;
import com.runying.po.User;
import com.runying.util.MD5Util;
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
	
	/**
	 * 获取所有未被删除的用户
	 * 
	 * @param pageNumber
	 * @param countPerPage
	 * @return
	 */
	public TableVO<User> getUndeletedUser(int pageNumber, int countPerPage) {
		List<User> us = userDaoProxy.findByColumn("status", 1, pageNumber, countPerPage);
		TableVO<User> tvo = new TableVO<User>();
		tvo.setRows(us);
		tvo.setCurrentPage(pageNumber);
		tvo.setCountPerPage(countPerPage);
		tvo.setPages((userDaoProxy.sizeWithStatus(1)-1) / countPerPage + 1);
		return tvo;
	}
	
	/**
	 * 修改用户密码
	 * 
	 * @param u
	 * @param operator
	 * @return
	 */
	public Msg editUserInfo(User u, User operator) {
		Msg msg = new Msg();
		
		User uDB = userDaoProxy.findByUsername(u.getUsername());
		//检查该用户是否存在
		if(uDB == null) {
			msg.setStatus(0);
			msg.setDescription("用户 " + u.getUsername() + " 不存在");
			return msg;
		}
		
		//检查密码是否合法（不为空）
		if(null == u.getPassword() || "".equals(u.getPassword())) {
			msg.setStatus(0);
			msg.setDescription("密码不能为空");
			return msg;
		}
		//密码加密后保存在数据库
		String passwordMD5 = MD5Util.string2MD5(u.getPassword());
		uDB.setPassword(passwordMD5);
		userDaoProxy.updat(uDB);
		
		msg.setStatus(1);
		return msg;
	}
	
	/**
	 * 修改用户权限
	 * 
	 * @param u
	 * @param operator
	 * @return
	 */
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
	
	/**
	 * 删除用户
	 * 
	 * @param u
	 * @param operator
	 * @return
	 */
	public Msg deleteUser(User u, User operator) {
		Msg msg = new Msg();
		
		User resDB = userDaoProxy.findByUsername(operator.getUsername());
		//检查操作员是否拥有 删除用户权限 的权限
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
		
		//不能删除自己
		if(uDB.getId() == operator.getId()) {
			msg.setStatus(0);
			msg.setDescription("不能删除自己啊喂！");
			return msg;
		}
		
		uDB.setStatus(0);
		userDaoProxy.updat(uDB);
		
		msg.setStatus(1);
		return msg;
	}
	
	/**
	 * 删除用户
	 * 
	 * @param u
	 * @param operator
	 * @return
	 */
	public Msg addUser(User u, User operator) {
		Msg msg = new Msg();
		
		User resDB = userDaoProxy.findByUsername(operator.getUsername());
		//检查操作员是否拥有 删除用户权限 的权限
		if(1 != (resDB.getPrivilege() >> 3 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		User uDB = userDaoProxy.findByUsername(u.getUsername());
		//检查该用户是否存在
		if(uDB != null) {
			msg.setStatus(0);
			msg.setDescription("用户 " + u.getUsername() + " 已存在");
			return msg;
		}
		
		//设置密码(初始密码 123)
		String md5Password = MD5Util.string2MD5("123");
		u.setPassword(md5Password);
		u.setStatus(1);
		userDaoProxy.addObject(u);
		
		msg.setStatus(1);
		return msg;
	}
}
