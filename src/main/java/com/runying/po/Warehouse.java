package com.runying.po;

/**
 * 仓库po
 * 
 * @author I319213
 *
 */
public class Warehouse {
	private int id;
	
	private Process process;
	
	//库存量
	private int number;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
