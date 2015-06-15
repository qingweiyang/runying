package com.runying.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统用户
 * @author yqw
 *
 */
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	//用户权限，000,000
	// ？？？，[入库/出库，增加产品到仓库][增加工序权限]［录入订单信息权限］
	@Column
	private int privilege;
	
	// 用户状态 000,000
	// [删除]
	@Column
	private int status;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
