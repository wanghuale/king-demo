package com.king.pojo;

import java.io.Serializable;

public class Organization implements Serializable {
    private Long id;

    private String name;

    private Long parentId;

    private String parentIds;

    private Boolean available;

    private static final long serialVersionUID = 1L;

    public Organization(Long id, String name, Long parentId, String parentIds, Boolean available) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.available = available;
    }

    public Organization() {
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}