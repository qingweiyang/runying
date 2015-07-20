package com.runying.dao;

import java.util.Calendar;
import java.util.Date;

import com.runying.po.InHouseLog;
import com.runying.util.DaoUtil;
import com.runying.vo.InHouseLogVO;
import com.runying.vo.TableVO;

public class InHouseLogDao extends DaoUtil{

	@Override
	protected String className() {
		return "inHouseLog";
	}
	
	public void addInHouseLog(InHouseLog inHouseLog) {
		//获取系统（服务器）当前时间
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		
		inHouseLog.setInHouseDate(date);
		this.addObject(inHouseLog);
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
		String hql = "select new com.runying.vo.InHouseLogVO(ihl.id, u.id as operatorID, u.username as operatorName, p.id as productID, p.materialName as productName, ihl.number, ihl.inHouseDate as date) "
				+ "from InHouseLog ihl, User u, Product p "
				+ "where (ihl.user = u and ihl.product = p) and (u.username like '%"+search+"%' or p.materialName like '%"+search+"%') "
				+ "order by ihl.inHouseDate desc";
		
		return this.excute(hql, pageNumber, countPerPage);
	}
}
