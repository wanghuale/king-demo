package com.king.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
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
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户中文名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 用户登陆名
     */
	@TableField("login_name")
	private String loginName;
    /**
     * 密码
     */
	private String password;
    /**
     * 盐值
     */
	private String salt;
    /**
     * 是否启用
     */
	private Integer enable;
    /**
     * 用户类型(1:注册用户，2：管理员添加)
     */
	private Integer type;
    /**
     * 创建日期
     */
	private Date created;
    /**
     * 更新日期
     */
	private Date upcreated;
    /**
     * 登录次数
     */
	@TableField("login_num")
	private Integer loginNum;
    /**
     * 当前登录时间
     */
	@TableField("login_time")
	private Date loginTime;
    /**
     * 上次登录时间
     */
	@TableField("old_login_time")
	private Date oldLoginTime;
    /**
     * 当前登录ip
     */
	@TableField("login_ip")
	private String loginIp;
    /**
     * 上次登录ip
     */
	@TableField("old_login_ip")
	private String oldLoginIp;


	public Long isId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String isUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String isLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String isPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String isSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer isEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer isType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date isCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date isUpcreated() {
		return upcreated;
	}

	public void setUpcreated(Date upcreated) {
		this.upcreated = upcreated;
	}

	public Integer isLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Date isLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date isOldLoginTime() {
		return oldLoginTime;
	}

	public void setOldLoginTime(Date oldLoginTime) {
		this.oldLoginTime = oldLoginTime;
	}

	public String isLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String isOldLoginIp() {
		return oldLoginIp;
	}

	public void setOldLoginIp(String oldLoginIp) {
		this.oldLoginIp = oldLoginIp;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", userName=" + userName +
			", loginName=" + loginName +
			", password=" + password +
			", salt=" + salt +
			", enable=" + enable +
			", type=" + type +
			", created=" + created +
			", upcreated=" + upcreated +
			", loginNum=" + loginNum +
			", loginTime=" + loginTime +
			", oldLoginTime=" + oldLoginTime +
			", loginIp=" + loginIp +
			", oldLoginIp=" + oldLoginIp +
			"}";
	}
}
