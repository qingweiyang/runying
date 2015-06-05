package com.runying.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.po.User;
import com.runying.util.Constants;
import com.runying.util.DaoUtil;
import com.runying.util.Msg;

@Controller
@RequestMapping(value={"font-design/log/", "font-design/warehouse_main", "font-design/warehouse"})
public class LogController {
	
	@RequestMapping(value = "login.do")
	@ResponseBody
	public Msg login(HttpServletRequest request, String username, String password) {
		Msg msg = new Msg();
		List<User> us = new DaoUtil().loginCheck(username, password);
		if(us.size() < 1) {
			msg.setStatus(0);
			return msg;
		}
		Map<String, Object> conts = new HashMap<String, Object>();
		conts.put("id", us.get(0).getId());
		conts.put("username", us.get(0).getUsername());
		msg.setStatus(1);
		msg.setConts(conts);
		//save login info to session
		request.getSession().setAttribute(Constants.USER, us.get(0));
		Constants.user = us.get(0);
		return msg;
	}
	
	@RequestMapping(value = "logout.do")
	@ResponseBody
	public Msg logout(HttpServletRequest request) {
		Msg msg = new Msg();
		//delete login info from session
		request.getSession().setAttribute(Constants.USER, null);
		msg.setStatus(1);
		return msg;
	}
}
