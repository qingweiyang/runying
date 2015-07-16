package com.runying.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.WarehouseDao;
import com.runying.po.Warehouse;
import com.runying.service.SalesBillService;
import com.runying.service.WarehouseService;
import com.runying.util.Constants;
import com.runying.util.Msg;
import com.runying.vo.OrdersBillDetailVO;
import com.runying.vo.OrdersBillVO;
import com.runying.vo.ProcessOrdersTableVO;
import com.runying.vo.TableVO;
import com.runying.vo.WarehouseVO;

@Controller
@RequestMapping(value={"font-design/warehouse/", "font-design/warehouse_main/"})
public class WarehouseController {
	@Autowired
	private WarehouseDao warehouseDaoProxy;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private SalesBillService salesBillService;
	
	@RequestMapping("/getWarehouses.do")
	@ResponseBody
	public TableVO<WarehouseVO> getWarehouses(int currentPage, int countPerPage, String search) {
		Map<String, Object> conds = new HashMap<String, Object>();
		//支持根据 产品名，规格型号1，规格型号2，物料长代码，材质，产品名拼音首字母缩写 模糊搜索
		conds.put("materialName", search);
		conds.put("size1", search);
		conds.put("size2", search);
		conds.put("materialCode", search);
		conds.put("material", search);
		conds.put("pinyin", search);
		
		return warehouseService.findWarehouses(conds, currentPage, countPerPage);
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
	
//	@RequestMapping("/outWarehouse.do")
//	@ResponseBody
//	public Msg outWarehouse(@RequestBody Warehouse w) {
//		return warehouseService.outWarehouse(Constants.user, w.getProduct(), w.getNumber());
//	}
	
	@RequestMapping("/outWarehouseBatch.do")
	@ResponseBody
	public Msg outWarehouseBatch(@RequestBody TableVO<ProcessOrdersTableVO> tvo) {
		return warehouseService.outWarehouseBatch(tvo.getRows(), Constants.user);
	}
	
	@RequestMapping("/getAll.do")
	@ResponseBody
	public TableVO<OrdersBillVO> getAll(int currentPage, int countPerPage) {
		return salesBillService.getAll(currentPage, countPerPage);
	}
	
	@RequestMapping("/getSalesbillDetail.do")
	@ResponseBody
	public List<OrdersBillDetailVO> getSalesbillDetail(int id) {
		return salesBillService.getDetail(id);
	}
}
