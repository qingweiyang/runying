package com.runying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.po.User;
import com.runying.service.UserService;

@Controller
@RequestMapping(value={"font-design/warehouse_main/", "font-design/warehouse"})
public class AdmUserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "getAllUser.do")
	@ResponseBody
	public List<User> getAllUser(int pageNumber, int countPerPage) {
		return userService.getAllUser(pageNumber, countPerPage);
	}
	
}
