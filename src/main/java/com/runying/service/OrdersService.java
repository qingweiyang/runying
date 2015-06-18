package com.runying.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.OrdersDao;
import com.runying.dao.WarehouseDao;
import com.runying.po.Orders;
import com.runying.vo.ProcessOrdersTableVO;
import com.runying.vo.TableVO;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersDao ordersDaoProxy;
	
	@Autowired
	private WarehouseDao warehouseDaoProxy;
	
	public TableVO<ProcessOrdersTableVO> getOrdersVOByStatus(int status, int currentPage, int countPerPage) {
		TableVO<ProcessOrdersTableVO> tvo = new TableVO<ProcessOrdersTableVO>();
		
		List<Orders> os = ordersDaoProxy.findByStatus(status, currentPage, countPerPage);
		List<ProcessOrdersTableVO> pos = new ArrayList<ProcessOrdersTableVO>();
		ProcessOrdersTableVO po = null;
		for(Orders o : os) {
			po = new ProcessOrdersTableVO();
			//找出该订单下产品的 库存量
			int count = warehouseDaoProxy.findByProduct(o.getProduct()).getNumber();
			po.setWarehouseCount(count);
			
			po.setCount(o.getCount());
			po.setDeliveryTime(o.getDeliveryTime());
			po.setId(o.getId());
			po.setMaterialName(o.getProduct().getMaterialName());
			po.setNumber(o.getNumber());
			po.setReleaseTime(o.getReleaseTime());
			po.setSize1(o.getProduct().getSize1());
			po.setSize2(o.getProduct().getSize2());
			po.setProcesses(o.getProcesses());
			
			pos.add(po);
		}
		tvo.setCountPerPage(countPerPage);
		tvo.setCurrentPage(currentPage);
		tvo.setPages((ordersDaoProxy.sizeWithStatus(status)-1) / countPerPage + 1);
		tvo.setRows(pos);
		
		return tvo;
	}

}
