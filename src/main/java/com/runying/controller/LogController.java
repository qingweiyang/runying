package com.runying.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.po.User;
import com.runying.util.DaoUtil;
import com.runying.util.Msg;

@Controller
@RequestMapping(value={"font-design/log/"})
public class LogController {
	
	@RequestMapping(value = "login.do")
	@ResponseBody
	public Msg login(String username, String password) {
		Msg msg = new Msg();
		List<User> us = DaoUtil.loginCheck(username, password);
		if(us.size() < 1) {
			msg.setStatus(0);
			return msg;
		}
		Map<String, Object> conts = new HashMap<String, Object>();
		conts.put("id", us.get(0).getId());
		conts.put("username", us.get(0).getUsername());
		msg.setStatus(1);
		msg.setConts(conts);
		return msg;
	}
}
