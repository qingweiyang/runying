package com.runying.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.po.User;
import com.runying.service.UserService;
import com.runying.util.Constants;
import com.runying.util.Msg;
import com.runying.vo.TableVO;

@Controller
@RequestMapping(value={"font-design/admConfig/"})
public class AdmUserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "getAllUser.do")
	@ResponseBody
	public TableVO<User> getAllUser(int currentPage, int countPerPage, String search) {
		Map<String, Object> conds = new HashMap<String, Object>();
		//支持根据 用户名 模糊搜索
		conds.put("username", search);
		
		return userService.findUsers(conds, currentPage, countPerPage);
	}
	
	@RequestMapping(value = "getSingleUser.do")
	@ResponseBody
	public User getSingleUser(int id) {
		return userService.findByID(id);
	}
	
	/**
	 * 修改用户权限
	 * @param currentPage
	 * @param countPerPage
	 * @return
	 */
	@RequestMapping(value = "editUserPrivilege.do")
	@ResponseBody
	public Msg editUserPrivilege(@RequestBody User user) {
		return userService.editUserPrivilege(user, Constants.user);
	}
	
	/**
	 * 删除用户权限
	 * @param currentPage
	 * @param countPerPage
	 * @return
	 */
	@RequestMapping(value = "deleteUser.do")
	@ResponseBody
	public Msg deleteUser(int id) {
		return userService.deleteUser(id, Constants.user);
	}
	
	/**
	 * 添加用户
	 * 
	 * @param 　user
	 * @return
	 */
	@RequestMapping(value = "addUser.do")
	@ResponseBody
	public Msg addUser(@RequestBody User user) {
		return userService.addUser(user, Constants.user);
	}
	
}
