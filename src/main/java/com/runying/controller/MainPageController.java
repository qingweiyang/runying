package com.runying.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.po.User;
import com.runying.util.Constants;

@Controller
@RequestMapping(value={"font-design/warehouse_main/", "font-design/warehouse", "font-design/admConfig/"})
public class MainPageController {

	@RequestMapping(value = "getCurUser.do")
	@ResponseBody
	public User getCurUser() {
		return Constants.user;
	}
	
}
