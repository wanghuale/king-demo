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
@TableName("sys_organization")
public class Organization extends Model<Organization> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String name;
	@TableField("parent_id")
	private Long parentId;
	@TableField("parent_ids")
	private String parentIds;
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
		return "Organization{" +
			"id=" + id +
			", name=" + name +
			", parentId=" + parentId +
			", parentIds=" + parentIds +
			", available=" + available +
			"}";
	}
}
