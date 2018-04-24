package com.king.pojo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Role implements Serializable {
    private Long id;

    private String role;

    private String description;

    private String alias;

    private String resourceIds;
    
    private String resourceStr;

    private Boolean available;
    
    private List<Resource> resources;

    private static final long serialVersionUID = 1L;

    public Role(Long id, String role, String description, String alias, String resourceIds, Boolean available) {
        this.id = id;
        this.role = role;
        this.description = description;
        this.alias = alias;
        this.resourceIds = resourceIds;
        this.available = available;
    }

    public List<Resource> getResources() {
		return resources;
	}


    

	public String getResourceStr() {
		String str ="";
		if(resources !=null && resources.size() >0){
			for (Resource resource : resources) {
				str+=resource.getName()+",";
			}
		}
		if(StringUtils.isNotBlank(str)){
			str=str.substring(0,str.lastIndexOf(","));
		}
		return str;
	}

	public void setResourceStr(String resourceStr) {
		this.resourceStr = resourceStr;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}



	public Role() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}