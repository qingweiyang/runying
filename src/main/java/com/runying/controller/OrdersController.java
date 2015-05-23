package com.runying.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runying.po.Orders;
import com.runying.util.DaoUtil;
import com.runying.util.Msg;

@RestController
public class OrdersController {
	
	@RequestMapping("/greeti")
	public Msg productIn(Orders o, int userID) {
		Msg msg = new Msg();
		msg.setStatus(1);
		
		DaoUtil.addObject(o);
		
		return msg;
	}
	
}
