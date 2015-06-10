package com.runying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.ProcessDao;
import com.runying.util.Constants;
import com.runying.util.Msg;
import com.runying.vo.OrdersProcessVO;

@Controller
@RequestMapping(value={"font-design/warehouse_main/"})
public class ProcessController {
	@Autowired
	private ProcessDao processDaoProxy;
	
	@RequestMapping("/addProcesses.do")
	@ResponseBody
	public Msg addProcesses(@RequestBody OrdersProcessVO opv) {
		return processDaoProxy.addProcesses(opv.getOrders(), Constants.user, opv.getPs());
	}
	
}
