package com.runying.po;

/**
 * 系统用户
 * @author yqw
 *
 */
public class User {

	private int id;
	
	private String username;
	
	private String password;
	
	//用户权限，000,000
	// ？？？，[入库/出库，增加产品到仓库][增加工序权限]［录入订单信息权限］
	private int privilege;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPrivilege() {
		return privilege;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

}
