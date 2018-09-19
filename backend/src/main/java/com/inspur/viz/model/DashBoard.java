package com.inspur.viz.model;

public class DashBoard {
	private String id;
	private String name;
	private String positionJson;
	private String createdAt;
	private String updatedAt;
	private String ownerId;
	private String isPublished;
	private String description;
	private String groupId;
	private String params;

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

	public String getPositionJson() {
		return positionJson;
	}

	public void setPositionJson(String positionJson) {
		this.positionJson = positionJson;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createAt) {
		this.createdAt = createAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
}
