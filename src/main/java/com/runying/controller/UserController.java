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

@Controller
@RequestMapping(value={"font-design/admConfig/", "font-design/warehouse_main/"})
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 修改用户权限
	 * @param currentPage
	 * @param countPerPage
	 * @return
	 */
	@RequestMapping(value = "editUserInfo.do")
	@ResponseBody
	public Msg editUserInfo(@RequestBody User user) {
		return userService.editUserInfo(user, Constants.user);
	}
}
