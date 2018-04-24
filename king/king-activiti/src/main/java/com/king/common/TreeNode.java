package com.king.common;

import java.io.Serializable;

public class TreeNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7708291833021539401L;

	private Long id;
	private Long pId;
	private String name;
	private boolean checked;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", pId=" + pId + ", name=" + name
				+ ", checked=" + checked + "]";
	}
	
	

}
