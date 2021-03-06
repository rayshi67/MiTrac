package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * StatusCode generated by hbm2java
 */
public class StatusCode implements java.io.Serializable {

	private StatusCodeId id;
	private String statusName;
	private String foregroundColor;
	private String backgroundColor;
	private String iconSelector;
	private String iconName;
	private String description;
	private Integer lastUpdateTime;
	private Integer creationTime;

	public StatusCode() {
	}

	public StatusCode(StatusCodeId id) {
		this.id = id;
	}

	public StatusCode(StatusCodeId id, String statusName,
			String foregroundColor, String backgroundColor,
			String iconSelector, String iconName, String description,
			Integer lastUpdateTime, Integer creationTime) {
		this.id = id;
		this.statusName = statusName;
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
		this.iconSelector = iconSelector;
		this.iconName = iconName;
		this.description = description;
		this.lastUpdateTime = lastUpdateTime;
		this.creationTime = creationTime;
	}

	public StatusCodeId getId() {
		return this.id;
	}

	public void setId(StatusCodeId id) {
		this.id = id;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getForegroundColor() {
		return this.foregroundColor;
	}

	public void setForegroundColor(String foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public String getBackgroundColor() {
		return this.backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getIconSelector() {
		return this.iconSelector;
	}

	public void setIconSelector(String iconSelector) {
		this.iconSelector = iconSelector;
	}

	public String getIconName() {
		return this.iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Integer lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Integer creationTime) {
		this.creationTime = creationTime;
	}

}
