package com.runying.util;

import com.runying.po.User;

public class Validate {

	/**
	 * 检查用户是否有相应的权限
	 * 
	 * @param u
	 * @param pri
	 * 			权限的位置，（[入库/出库，增加产品到仓库][增加工序权限]［录入订单信息权限］），如 3 表示 [入库/出库]
	 * @return	msg
	 */
	public static Msg checkUserPrivilege(User u, int pri) {
		Msg msg = new Msg();
		
		if(null == u) {
			msg.setStatus(0);
			msg.setDescription("用户不存在");
			return msg;
		}
		
		//检查操作员是否拥有 删除用户权限 的权限
		if(1 != (u.getPrivilege() >> pri & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
		} else {
			msg.setStatus(1);
		}
		
		return msg;
	}
	
}
