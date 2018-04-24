package com.king.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;

public class User implements Serializable {
    @Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", loginName="
				+ loginName + ", password=" + password + ", salt=" + salt
				+ ", enable=" + enable + ", type=" + type + ", created="
				+ created + ", upcreated=" + upcreated + ", loginNum="
				+ loginNum + ", loginTime=" + loginTime + ", roleIds="
				+ roleIds + ", roleIdsStr=" + roleIdsStr + ", locked=" + locked
				+ ", oldLoginTime=" + oldLoginTime + ", loginIp=" + loginIp
				+ ", oldLoginIp=" + oldLoginIp + ", roles=" + roles + "]";
	}

	private Long id;

    private String userName;

    private String loginName;

    private String password;

    private String salt;

    private Integer enable;

    private Integer type;

    private Date created;

    private Date upcreated;

    private Integer loginNum;

    private Date loginTime;

    private List<Long> roleIds;
    
    private String roleIdsStr;

	private Boolean locked = Boolean.TRUE;

    private Date oldLoginTime;

    private String loginIp;

    private String oldLoginIp;
    
    private List<Role> roles;

    public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	private static final long serialVersionUID = 1L;

    public User(Long id, String userName, String loginName, String password, String salt, Integer enable, Integer type, Date created, Date upcreated, Integer loginNum, Date loginTime, List<Long> roleIds, Boolean locked, Date oldLoginTime, String loginIp, String oldLoginIp) {
        this.id = id;
        this.userName = userName;
        this.loginName = loginName;
        this.password = password;
        this.salt = salt;
        this.enable = enable;
        this.type = type;
        this.created = created;
        this.upcreated = upcreated;
        this.loginNum = loginNum;
        this.loginTime = loginTime;
        this.roleIds = roleIds;
        this.locked = locked;
        this.oldLoginTime = oldLoginTime;
        this.loginIp = loginIp;
        this.oldLoginIp = oldLoginIp;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public String getCredentialsSalt() {
		return loginName + salt;
	}
    public String getRoleIdsStr() {
    	if (CollectionUtils.isEmpty(roleIds)) {
			return "";
		}
		StringBuilder s = new StringBuilder();
		for (Long roleId : roleIds) {
			s.append(roleId);
			s.append(",");
		}
		return s.toString();
	}

	public void setRoleIdsStr(String roleIdsStr) {
		if (StringUtils.isEmpty(roleIdsStr)) {
			return;
		}
		String[] roleIdStrs = roleIdsStr.split(",");
		for (String roleIdStr : roleIdStrs) {
			if (StringUtils.isEmpty(roleIdStr)) {
				continue;
			}
			getRoleIds().add(Long.valueOf(roleIdStr));
		}
	}

	public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getType() {
        return type;
    }

    public List<Long> getRoleIds() {
    	
    	if (roleIds == null) {
			roleIds = new ArrayList<Long>();
		}
		return roleIds;
	}
    
    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpcreated() {
        return upcreated;
    }

    public void setUpcreated(Date upcreated) {
        this.upcreated = upcreated;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getOldLoginTime() {
        return oldLoginTime;
    }

    public void setOldLoginTime(Date oldLoginTime) {
        this.oldLoginTime = oldLoginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public String getOldLoginIp() {
        return oldLoginIp;
    }

    public void setOldLoginIp(String oldLoginIp) {
        this.oldLoginIp = oldLoginIp == null ? null : oldLoginIp.trim();
    }
}