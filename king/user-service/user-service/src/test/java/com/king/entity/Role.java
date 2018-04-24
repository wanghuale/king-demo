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
@TableName("sys_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String role;
	private String description;
	private String alias;
	@TableField("resource_ids")
	private String resourceIds;
	private Integer available;


	public Long isId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String isRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String isDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String isAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String isResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
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
		return "Role{" +
			"id=" + id +
			", role=" + role +
			", description=" + description +
			", alias=" + alias +
			", resourceIds=" + resourceIds +
			", available=" + available +
			"}";
	}
}
