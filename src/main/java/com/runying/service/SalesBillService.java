package com.runying.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.SalesBillDao;
import com.runying.po.SalesBill;
import com.runying.po.SalesBill_Orders;
import com.runying.vo.OrdersBillDetailVO;
import com.runying.vo.OrdersBillVO;
import com.runying.vo.TableVO;

@Service
public class SalesBillService {
	@Autowired
	private SalesBillDao salesBillDaoProxy;
	
	public TableVO<OrdersBillVO> getAll(int pageNumber, int countPerPage) {
		List<SalesBill> us = salesBillDaoProxy.findAll(pageNumber, countPerPage);
		
		//返回给前端数据格式为 OrdersBillVO
		List<OrdersBillVO> obvos = new ArrayList<OrdersBillVO>();
		for(SalesBill sb : us) {
			OrdersBillVO ob = new OrdersBillVO();
			ob.setid(sb.getId());
			ob.setOperator(sb.getOperator().getUsername());
			ob.setSystemTime(sb.getSystemTime());
			obvos.add(ob);
		}
		
		TableVO<OrdersBillVO> tvo = new TableVO<OrdersBillVO>();
		tvo.setRows(obvos);
		tvo.setCurrentPage(pageNumber);
		tvo.setCountPerPage(countPerPage);
		tvo.setPages((salesBillDaoProxy.size()-1) / countPerPage + 1);
		return tvo;
	}
	
	/**
	 * 根据发货单id获取该发货单的详细信息
	 * 
	 * @param id
	 * @return 
	 */
	public List<OrdersBillDetailVO> getDetail(int id) {
		List<OrdersBillDetailVO> obds = new ArrayList<OrdersBillDetailVO>();
		
		SalesBill sb = salesBillDaoProxy.findByID(id);
		Set<SalesBill_Orders> sbos = sb.getSalesBill_Orders(); 
		for(SalesBill_Orders sbo : sbos) {
			OrdersBillDetailVO ob = new OrdersBillDetailVO();
			ob.setProductName(sbo.getOrders().getProduct().getMaterialName());
			ob.setCount(sbo.getCount());
			ob.setSize(sbo.getOrders().getProduct().getSize1());
			
			obds.add(ob);
		}
		
		return obds;
	}
	
}
