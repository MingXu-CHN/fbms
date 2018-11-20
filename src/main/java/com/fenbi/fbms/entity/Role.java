package com.fenbi.fbms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable {

	private static final long serialVersionUID = -7269072666094435775L;
	
	private Integer id;
	private String name;
	private Timestamp createTime;
	private List<Function> roleFunctions = new ArrayList<Function>();
	
	
	public List<Function> getRoleFunctions() {
		return roleFunctions;
	}
	public void setRoleFunctions(List<Function> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	

}
