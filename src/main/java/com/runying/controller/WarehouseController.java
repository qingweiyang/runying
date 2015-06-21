package com.runying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.WarehouseDao;
import com.runying.po.Warehouse;
import com.runying.service.WarehouseService;
import com.runying.util.Constants;
import com.runying.util.Msg;
import com.runying.vo.ProcessOrdersTableVO;
import com.runying.vo.TableVO;

@Controller
@RequestMapping(value={"font-design/warehouse/", "font-design/warehouse_main/"})
public class WarehouseController {
	@Autowired
	private WarehouseDao warehouseDaoProxy;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@RequestMapping("/getWarehouses.do")
	@ResponseBody
	public TableVO<Warehouse> getWarehouses(int currentPage, int countPerPage) {
		List<Warehouse> os = warehouseDaoProxy.findAll(currentPage, countPerPage);
		TableVO<Warehouse> tvo = new TableVO<Warehouse>();
		tvo.setRows(os);
		tvo.setCurrentPage(currentPage);
		tvo.setCountPerPage(countPerPage);
		tvo.setPages((warehouseDaoProxy.size()-1) / countPerPage + 1);
		return tvo;
	}
	
	@RequestMapping("/inWarehouse.do")
	@ResponseBody
	public Msg inWarehouse(@RequestBody Warehouse w) {
		return warehouseService.inWarehouse(Constants.user, w.getProduct(), w.getNumber());
	}
	
	@RequestMapping("/inWarehouseBatch.do")
	@ResponseBody
	public Msg inWarehouseBatch(@RequestBody TableVO<ProcessOrdersTableVO> tvo) {
		return warehouseService.inWarehouseBatch(tvo.getRows(), Constants.user);
	}
	
	@RequestMapping("/outWarehouse.do")
	@ResponseBody
	public Msg outWarehouse(@RequestBody Warehouse w) {
		return warehouseService.outWarehouse(Constants.user, w.getProduct(), w.getNumber());
	}
	
	@RequestMapping("/outWarehouseBatch.do")
	@ResponseBody
	public Msg outWarehouseBatch(@RequestBody TableVO<ProcessOrdersTableVO> tvo) {
		return warehouseService.outWarehouseBatch(tvo.getRows(), Constants.user);
	}
}
