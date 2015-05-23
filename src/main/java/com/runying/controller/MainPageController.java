package com.runying.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.po.User;
import com.runying.util.Constants;
import com.runying.util.DaoUtil;

@Controller
@RequestMapping(value={"font-design/warehouse_main/"})
public class MainPageController {

	@RequestMapping(value = "getCurUser.do")
	@ResponseBody
	public User getCurUser() {
		return DaoUtil.getObject(User.class, Constants.userID);
	}
	
}
