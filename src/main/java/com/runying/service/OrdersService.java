package com.runying.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.OrdersDao;
import com.runying.dao.WarehouseDao;
import com.runying.po.Orders;
import com.runying.po.Warehouse;
import com.runying.vo.OrdersDetailVO;
import com.runying.vo.ProcessOrdersTableVO;
import com.runying.vo.TableVO;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersDao ordersDaoProxy;
	
	@Autowired
	private WarehouseDao warehouseDaoProxy;
	
	public TableVO<ProcessOrdersTableVO> getOrdersVOByMultiStatus(List<Object> status, int currentPage, int countPerPage) {
		TableVO<ProcessOrdersTableVO> tvo = new TableVO<ProcessOrdersTableVO>();
		
		List<Orders> os = ordersDaoProxy.findByMultiStatus(status, currentPage, countPerPage);
		List<ProcessOrdersTableVO> pos = new ArrayList<ProcessOrdersTableVO>();
		ProcessOrdersTableVO po = null;
		for(Orders o : os) {
			po = new ProcessOrdersTableVO();
			//找出该订单下产品的 库存量
			int count = 0;
			Warehouse tmp = warehouseDaoProxy.findByProduct(o.getProduct());
			if(tmp != null) {
				count = warehouseDaoProxy.findByProduct(o.getProduct()).getNumber();
			}
			po.setWarehouseCount(count);
			
			po.setProductID(o.getProduct().getId());
			po.setCount(o.getCount());
			po.setDeliveryTime(o.getDeliveryTime());
			po.setId(o.getId());
			po.setMaterialName(o.getProduct().getMaterialName());
			po.setNumber(o.getNumber());
			po.setReleaseTime(o.getReleaseTime());
			po.setSize1(o.getProduct().getSize1());
			po.setSize2(o.getProduct().getSize2());
			po.setProcesses(o.getProcesses());
			po.setHasFinished(o.getHasFinished());
			
			pos.add(po);
		}
		tvo.setCountPerPage(countPerPage);
		tvo.setCurrentPage(currentPage);
		tvo.setPages((ordersDaoProxy.sizeWithMultiStatus(status)-1) / countPerPage + 1);
		tvo.setRows(pos);
		
		return tvo;
	}

	/**
	 * 获取未删除的该页下订单数据
	 * 
	 * @param currentPage
	 * @param countPerPage
	 * @return
	 */
	public TableVO<OrdersDetailVO> getUndeleteOrdersTVO(int currentPage, int countPerPage) {
		List<Object> sts = new ArrayList<Object>();
		for(int i = 1 ; i <= 5 ; i++ ) {
			sts.add(i);
		}
		TableVO<OrdersDetailVO> tvo = new TableVO<OrdersDetailVO>();
		List<Orders> os = ordersDaoProxy.findByMultiStatus(sts, currentPage, countPerPage);
		List<OrdersDetailVO> odvo = new ArrayList<OrdersDetailVO>();
		
		for(Orders o : os) {
			OrdersDetailVO oo = new OrdersDetailVO();
			
			oo.setId(o.getId());
			oo.setContractNumber(o.getContractNumber());
			oo.setCount(o.getCount());
			oo.setDeliveryTime(o.getDeliveryTime());
			oo.setReleaseTime(o.getReleaseTime());
			oo.setHasFinished(o.getHasFinished());
			oo.setProductID(o.getProduct().getId());
			oo.setMaterialName(o.getProduct().getMaterialName());
			oo.setOperator(o.getOperator().getUsername());
			oo.setSystemTime(o.getSystemTime());
			oo.setStatus(o.getStatus());
			
			odvo.add(oo);
		}
		tvo.setCountPerPage(countPerPage);
		tvo.setCurrentPage(currentPage);
		tvo.setPages((ordersDaoProxy.sizeWithMultiStatus(sts)-1) / countPerPage + 1);
		tvo.setRows(odvo);
		
		return tvo;
	}
}
