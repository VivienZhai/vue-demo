package com.inspur.viz.model;

public class DataView {
	private String id;
	private String name;
	private String dataConnectionId;
	private String ownerId;
	private String description;
	private String queryMode;
	private String querySql;
	private String params;
	private String updatedAt;
	private String createdAt;
	private String useCache;

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

	public String getDataConnectionId() {
		return dataConnectionId;
	}

	public void setDataConnectionId(String dataConnectionId) {
		this.dataConnectionId = dataConnectionId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}

	public String getQuerySql() {
		return querySql;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUseCache() {
		return useCache;
	}

	public void setUseCache(String useCache) {
		this.useCache = useCache;
	}
}
