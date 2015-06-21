package com.runying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.po.User;
import com.runying.service.UserService;
import com.runying.util.Constants;

@Controller
@RequestMapping(value={"font-design/warehouse_main/", "font-design/warehouse", "font-design/admConfig/"})
public class MainPageController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "getCurUser.do")
	@ResponseBody
	public User getCurUser() {
		Constants.user = userService.findByUsername(Constants.user.getUsername());
		return Constants.user;
	}
	
}
