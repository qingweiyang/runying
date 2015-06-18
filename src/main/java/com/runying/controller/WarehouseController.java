package com.runying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.WarehouseDao;
import com.runying.po.Warehouse;
import com.runying.util.Constants;
import com.runying.util.Msg;
import com.runying.vo.ProcessOrdersTableVO;
import com.runying.vo.TableVO;

@Controller
@RequestMapping(value={"font-design/warehouse/", "font-design/warehouse_main/"})
public class WarehouseController {
	@Autowired
	private WarehouseDao warehouseDaoProxy;
	
	@RequestMapping("/getWarehouses.do")
	@ResponseBody
	public List<Warehouse> getWarehouses() {
		return warehouseDaoProxy.findAll(1, 5);
	}
	
	@RequestMapping("/inWarehouse.do")
	@ResponseBody
	public Msg inWarehouse(@RequestBody Warehouse w) {
		return warehouseDaoProxy.inWarehouse(Constants.user, w.getProduct(), w.getNumber());
	}
	
	@RequestMapping("/inWarehouseBatch.do")
	@ResponseBody
	public void inWarehouseBatch(@RequestBody TableVO<ProcessOrdersTableVO> tvo) {
		System.out.println("================="+tvo.getRows().size());
	}
	
	@RequestMapping("/outWarehouse.do")
	@ResponseBody
	public Msg outWarehouse(@RequestBody Warehouse w) {
		return warehouseDaoProxy.outWarehouse(Constants.user, w.getProduct(), w.getNumber());
	}
}
