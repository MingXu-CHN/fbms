package com.fenbi.fbms.entity;

import java.util.HashMap;

public class Page extends HashMap<String, Object> {
	private static final long serialVersionUID = -6180377661907437656L;
	//每页显示多少条数据
	public static final int PAGE_SIZE = 5;
	
	public Page(int current) {
		super();
		this.put("current", current);
		this.put("size", PAGE_SIZE);
		this.put("begin", (current - 1) * PAGE_SIZE);
	}

}
