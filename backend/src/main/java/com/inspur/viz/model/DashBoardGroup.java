package com.inspur.viz.model;

import java.util.List;
import java.util.Map;

public class DashBoardGroup {
	private String id;
	private String name;
	private String description;
	private String parentId;
	private String ownerId;
	private String createdAt;
	private String updatedAt;
	private Integer orderNum;
	private List<Map<String, String>> dashboards;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public List<Map<String, String>> getDashboards() {
		return dashboards;
	}

	public void setDashboards(List<Map<String, String>> dashboards) {
		this.dashboards = dashboards;
	}
}
