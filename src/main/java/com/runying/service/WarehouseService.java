package com.runying.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.InHouseLogDao;
import com.runying.dao.OrdersDao;
import com.runying.dao.ProductDao;
import com.runying.dao.SalesBillDao;
import com.runying.dao.SalesBill_OrdersDao;
import com.runying.dao.UserDao;
import com.runying.dao.WarehouseDao;
import com.runying.po.InHouseLog;
import com.runying.po.Orders;
import com.runying.po.Product;
import com.runying.po.SalesBill;
import com.runying.po.SalesBill_Orders;
import com.runying.po.User;
import com.runying.po.Warehouse;
import com.runying.util.Constants;
import com.runying.util.Msg;
import com.runying.util.Validate;
import com.runying.vo.InHouseLogVO;
import com.runying.vo.ProcessOrdersTableVO;
import com.runying.vo.TableVO;
import com.runying.vo.WarehouseVO;

@Service
public class WarehouseService {
	@Autowired
	private WarehouseDao warehouseDaoProxy;
	
	@Autowired
	private InHouseLogDao inHouseLogDaoProxy;
	
	@Autowired
	private ProductDao productDaoProxy;
	
	@Autowired
	private UserDao userDaoProxy;
	
	@Autowired
	private OrdersDao ordersDaoProxy;
	
	@Autowired 
	private SalesBillDao salesBillDaoProxy;
	
	@Autowired
	private SalesBill_OrdersDao salesBill_OrdesDaoProxy;
	
	public Msg outWarehouseBatch(List<ProcessOrdersTableVO> rows, User operator) {
		//检查操作员是否拥有 入库出库 权限
		User oDB = userDaoProxy.findByUsername(operator.getUsername());
		Msg msg = Validate.checkUserPrivilege(oDB, Constants.WAREHOUSE_IN_OUT); 
		if(msg.getStatus() == 0) {
			return msg;
		}
		
		//检查是否能批量出货（如果有一个不能，则出货失败，所有均未出货）
		msg = checkOutWarehouse(rows);
		if(msg.getStatus() == 0)
			return msg;
		
		//批量出库
		for(ProcessOrdersTableVO ptvo : rows) {
			List<Warehouse> wh = warehouseDaoProxy.findByColumn("product", (Product) productDaoProxy.findByID(ptvo.getProductID()));
			Warehouse w = wh.get(0);
			int num = ptvo.getCount();
			w.setNumber(w.getNumber()-num);
			warehouseDaoProxy.updat(w);
			//出库完成，修改订单状态,
			//订单还未全部完成
			//先找到订单的数据库中保存的数量（从前端传来值非数据库值，是用户输入的当前入库数量）
			Orders ordersDB = ordersDaoProxy.findByID(ptvo.getId());
			int planCount = ordersDB.getCount();
			int hasFinished = ordersDB.getHasFinished() + ptvo.getCount();
			if(planCount > hasFinished) {
				//此时订单未全部完成
				ordersDB.setStatus(4);
			} else {
				//此时订单全部完成
				ordersDB.setStatus(5);
			}
			ordersDB.setHasFinished(hasFinished);
			ordersDaoProxy.updat(ordersDB);
		}
		
		//保存操作日志
		logOutWarehouseBill(rows, operator);
		
		msg.setStatus(1);
		return msg;
	}
	
	/**
	 * 纪录出库单保存
	 * 
	 * @param odb
	 * @param operator
	 */
	private void logOutWarehouseBill(List<ProcessOrdersTableVO> rows, User operator) {
		SalesBill salesBill = new SalesBill();
		//获取系统（服务器）当前时间
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		salesBill.setSystemTime(date);
		
		//保存操作员
		salesBill.setOperator(userDaoProxy.findByUsername(operator.getUsername()));
		
		//先在数据中保存一个 出库单
		int id = salesBillDaoProxy.addObject(salesBill);
		salesBill = salesBillDaoProxy.findByID(id);
		
		for(ProcessOrdersTableVO ptvo : rows) {
			SalesBill_Orders so = new SalesBill_Orders();
			so.setOrders((Orders)ordersDaoProxy.findByID(ptvo.getId()));
			so.setSalesBill(salesBill);
			so.setCount(ptvo.getCount());
			//保存 出货单与订单 连接表
			salesBill_OrdesDaoProxy.addObject(so);
		}
		salesBillDaoProxy.addObject(salesBill);
	}
	
	/**
	 * 检查从前端输入的产品能否出库
	 * 
	 * @return
	 */
	private Msg checkOutWarehouse(List<ProcessOrdersTableVO> rows) {
		Msg msg = new Msg();
		
		for(ProcessOrdersTableVO ptvo : rows) {
			int num = ptvo.getCount();
			//出库数量是否合法
			if(num <= 0) {
				msg.setStatus(0);
				msg.setDescription("出库数量错误，必须大于0");
				return msg;
			}
			
			//出库操作
			//先检查仓库中是否已有该产品
			List<Warehouse> wh = warehouseDaoProxy.findByColumn("product", (Product) productDaoProxy.findByID(ptvo.getProductID()));
			if(wh == null || wh.size() == 0) {
				//没有该产品
				msg.setStatus(0);
				msg.setDescription("仓库没有该商品");
				return msg;
			} else {
				Warehouse w = wh.get(0);
				//库存量 是否足够
				int wNum = w.getNumber();
				if(wNum < num) {
					msg.setStatus(0);
					msg.setDescription("商品 "+w.getProduct().getMaterialName()+" 库存量不足");
					return msg;
				} 
			}
			
		}
		
		msg.setStatus(1);
		return msg;
	}
	
//	/**
//	 * 出库
//	 * @return
//	 */
//	public Msg outWarehouse(User operator, Product product, int num) {
//		Msg msg = new Msg();
//		
//		//检查操作员是否有权限
//		User oDB = userDaoProxy.findByUsername(operator.getUsername());
//		if(1 != (oDB.getPrivilege() >> 2 & 1)) {
//			msg.setStatus(0);
//			msg.setDescription("权限不足");
//			return msg;
//		}
//		
//		//出库数量是否合法
//		if(num <= 0) {
//			msg.setStatus(0);
//			msg.setDescription("出库数量错误，必须大于0");
//			return msg;
//		}
//		
//		//出库操作
//		//先检查仓库中是否已有该产品
//		List<Warehouse> wh = warehouseDaoProxy.findByColumn("product", product);
//		if(wh == null || wh.size() == 0) {
//			//没有该产品
//			msg.setStatus(0);
//			msg.setDescription("仓库没有该商品");
//			return msg;
//		} else {
//			Warehouse w = wh.get(0);
//			//库存量 是否足够
//			int wNum = w.getNumber();
//			if(wNum < num) {
//				msg.setStatus(0);
//				msg.setDescription("商品 "+w.getProduct().getMaterialName()+" 库存量不足");
//				return msg;
//			} else {	
//				w.setNumber(w.getNumber()-num);
//				warehouseDaoProxy.updat(w);
//			}
//			
//		}
//		
//		msg.setStatus(1);
//		return msg;
//	}
	
	/**
	 * 入库
	 * @return
	 */
	public Msg inWarehouse(User operator, Product product, int num) {
		Msg msg = new Msg();
		
		//检查操作员是否有权限
		User oDB = userDaoProxy.findByUsername(operator.getUsername());
		if(1 != (oDB.getPrivilege() >> 2 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		//入库数量是否合法
		if(num <= 0) {
			msg.setStatus(0);
			msg.setDescription("入库数量错误，必须大于0");
			return msg;
		}
		
		//入库操作
		//先检查仓库中是否已有该产品
		List<Warehouse> wh = warehouseDaoProxy.findByColumn("product", product);
		if(wh == null || wh.size() == 0) {
			//没有该产品
			Warehouse w = new Warehouse();
			w.setNumber(num);
			w.setProduct(product);
			warehouseDaoProxy.addObject(w);
		} else {
			Warehouse w = wh.get(0);
			w.setNumber(w.getNumber()+num);
			warehouseDaoProxy.updat(w);
		}
		
		msg.setStatus(1);
		return msg;
	}
	
	/**
	 * 入库操作成功后， 记录入库日志
	 * @param operator
	 * @param product
	 * @param num
	 */
	public void logInWarehouse(User operator, Product product, int num) {
		InHouseLog inHouseLog = new InHouseLog();
		inHouseLog.setUser(operator);
		inHouseLog.setProduct(product);
		inHouseLog.setNumber(num);
		
		inHouseLogDaoProxy.addInHouseLog(inHouseLog);
		
	}
	
	public Msg inWarehouseBatch(List<ProcessOrdersTableVO> rows, User operator) {
		//检查操作员是否拥有 入库出库 权限
		User oDB = userDaoProxy.findByUsername(operator.getUsername());
		Msg msg = Validate.checkUserPrivilege(oDB, Constants.WAREHOUSE_IN_OUT); 
		if(msg.getStatus() == 0) {
			return msg;
		}
		
		//批量入库
		for(ProcessOrdersTableVO ptvo : rows) {
			msg = this.inWarehouse(operator, (Product) productDaoProxy.findByID(ptvo.getProductID()), ptvo.getCount());
			if(msg.getStatus() == 0) {
				return msg;
			} else {
				//出库完成，修改订单状态, 已入库
				ordersDaoProxy.updateStatus(ptvo.getId(), 3);
			}
		}
		
		return msg;
	}

	/**
	 * 模糊搜索，产品状态未删除
	 * 
	 * @param colsLike
	 * @param pageNumber
	 * @param countPerPage
	 * @return
	 */
	public TableVO<WarehouseVO> findWarehouses(Map<String, Object> colsLike, int pageNumber, int countPerPage) {
		Map<String, Object> colsEqual = new HashMap<String, Object>();
		colsEqual.put("status", 1);
		
		TableVO<Product> pstvo = productDaoProxy.findByConditions(colsLike, "or", colsEqual, "or", "pinyin", pageNumber, countPerPage);
		TableVO<WarehouseVO> whtvo = new TableVO<WarehouseVO>();
		List<WarehouseVO> whs = new ArrayList<WarehouseVO>();
		for(Product p : pstvo.getRows()) {
			WarehouseVO wvo = new WarehouseVO();
			wvo.setWarehouseID(p.getWarehouse().getId());
			wvo.setProductID(p.getId());
			wvo.setMaterialName(p.getMaterialName());
			wvo.setSize1(p.getSize1());
			wvo.setSize2(p.getSize2());
			wvo.setMaterial(p.getMaterial());
			wvo.setMaterialCode(p.getMaterialCode());
			wvo.setWarehouseCount(p.getWarehouse().getNumber());
			
			whs.add(wvo);
		}
		whtvo.setRows(whs);
		whtvo.setCountPerPage(countPerPage);
		whtvo.setCurrentPage(pageNumber);
		whtvo.setPages(pstvo.getPages());
		
		return whtvo;
	}
	
	/**
	 * 根据关键字模糊查询 InHouseLog, User, Product 连接表
	 * 
	 * @param search
	 * @param pageNumber
	 * @param countPerPage
	 * @return
	 */
	public TableVO<InHouseLogVO> findInHouseLogVO(String search, int pageNumber, int countPerPage) {
		return inHouseLogDaoProxy.findInHouseLogVO(search, pageNumber, countPerPage);
	}
}
