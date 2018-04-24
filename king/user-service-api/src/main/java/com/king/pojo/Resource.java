package com.king.pojo;

import java.io.Serializable;

public class Resource implements Serializable {
    private Long id;

    private String name;

    private String type;

    private String url;

    private Long parentId;

    private String parentIds;

    private String permission;

    private Boolean available;
    
    
    private boolean rootNode;

	
	
	public void setRootNode(boolean rootNode) {
		this.rootNode = rootNode;
	}
	public boolean isRootNode(){
		return parentId == 0;
	}
    public String makeSelfAsParentIds() {
		return getParentIds() + getId() + "/";
	}
    
    public String getText(){
    	return ResourceType.byType(type);
    }
    
    public static enum ResourceType {
		menu("菜单"), button("按钮");

		private final String text;

		private ResourceType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
		
		public static String byType(String type) {
			for (ResourceType constant : ResourceType.values()) {
				if (constant.name().equals(type))
					return constant.name();
			}
			throw new IllegalArgumentException("No ResourceType constant with id=" + type + " exist.");
		}
	}
    
    private static final long serialVersionUID = 1L;

    public Resource(Long id, String name, String type, String url, Long parentId, String parentIds, String permission, Boolean available) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.permission = permission;
        this.available = available;
    }

    public Resource() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}