package com.runying.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.OrdersDao;
import com.runying.dao.ProcessDao;
import com.runying.dao.UserDao;
import com.runying.po.Orders;
import com.runying.po.Process;
import com.runying.po.User;
import com.runying.util.Msg;

@Service
public class ProcessService {
	@Autowired
	private ProcessDao processDaoProxy;
	
	@Autowired
	private UserDao userDaoProxy;
	
	@Autowired
	private OrdersDao ordersDaoProxy;
	
	/**
	 * 增加多道工序
	 * 
	 * @param o
	 * @param ps
	 * @return
	 */
	public Msg addProcesses(Orders o, User responsible, List<Process> ps) {
		Msg msg = new Msg();
		
		User resDB = userDaoProxy.findByUsername(responsible.getUsername());
		//检查操作员是否拥有增加工序权限
		if(1 != (resDB.getPrivilege() >> 1 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		//检查订单状态
		Orders oDB = ordersDaoProxy.findByID(o.getId());
		if(oDB.getStatus() != 1) {
			//状态不是 未进行生产计划 ， 这里只处理 未进行生产计划 的订单
			msg.setStatus(0);
			msg.setDescription("状态不是 未进行生产计划");
			return msg;
		}
		
		//未制定任何工序
		if(null == ps || ps.size() == 0) {
			msg.setStatus(0);
			msg.setDescription("您还未制定任何工序");
			return msg;
		}
		
		for(Process p : ps) {
			//检查用户是否存在 
			User receiverDB = userDaoProxy.findByUsername(p.getReceiver().getUsername());
			if(null == receiverDB) {
				msg.setStatus(0);
				msg.setDescription("接收者 "+p.getReceiver().getUsername()+" 不存在");
				return msg;
			}
			
			//本工序数量必须小于等于上一道工序
			if(1 != p.getProcessNum() && p.getNum() > ps.get(p.getProcessNum()-2).getNum()) {
				//当前工序数量大于前一道工序数量，错误
				msg.setStatus(0);
				msg.setDescription("工序数量必须小于或等于前一道工序数量");
				return msg;
			}
			
			//保存一道工序
			//获取系统（服务器）当前时间
			Calendar now = Calendar.getInstance();
			Date date = now.getTime();
			p.setSystemTime(date);
			p.setReceiver(receiverDB);
			p.setOrders(oDB);
			processDaoProxy.addObject(p);
		}
		//修改订单状态为 2:已进行生产计划
		oDB.setStatus(2);
		ordersDaoProxy.updat(oDB);
		
		msg.setStatus(1);
		return msg;
	}
	
}
