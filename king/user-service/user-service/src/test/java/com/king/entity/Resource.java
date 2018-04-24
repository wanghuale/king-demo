package com.king.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanghuay
 * @since 2017-07-12
 */
@TableName("sys_resource")
public class Resource extends Model<Resource> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String name;
	private String type;
	private String url;
	@TableField("parent_id")
	private Long parentId;
	@TableField("parent_ids")
	private String parentIds;
	private String permission;
	private Integer available;


	public Long isId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String isName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String isType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String isUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long isParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String isParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String isPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer isAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Resource{" +
			"id=" + id +
			", name=" + name +
			", type=" + type +
			", url=" + url +
			", parentId=" + parentId +
			", parentIds=" + parentIds +
			", permission=" + permission +
			", available=" + available +
			"}";
	}
}
