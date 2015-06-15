package com.runying.controller;

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
	public TableVO<User> getAllUser(int currentPage, int countPerPage) {
		return userService.getAllUser(currentPage, countPerPage);
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
}
