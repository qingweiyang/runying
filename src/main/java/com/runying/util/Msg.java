package com.runying.util;

import java.util.Map;

public class Msg {

	private int status;
	
	private Map<String, Object> conts;
	
	private String description;

	public Msg() {
		this.status = 1;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, Object> getConts() {
		return conts;
	}

	public void setConts(Map<String, Object> conts) {
		this.conts = conts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
