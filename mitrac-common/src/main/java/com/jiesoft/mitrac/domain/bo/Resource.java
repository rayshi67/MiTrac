package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * Resource generated by hbm2java
 */
public class Resource implements java.io.Serializable {

	private ResourceId id;
	private String type;
	private String title;
	private String properties;
	private byte[] value;
	private String displayName;
	private String description;
	private Integer lastUpdateTime;
	private Integer creationTime;

	public Resource() {
	}

	public Resource(ResourceId id) {
		this.id = id;
	}

	public Resource(ResourceId id, String type, String title,
			String properties, byte[] value, String displayName,
			String description, Integer lastUpdateTime, Integer creationTime) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.properties = properties;
		this.value = value;
		this.displayName = displayName;
		this.description = description;
		this.lastUpdateTime = lastUpdateTime;
		this.creationTime = creationTime;
	}

	public ResourceId getId() {
		return this.id;
	}

	public void setId(ResourceId id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProperties() {
		return this.properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public byte[] getValue() {
		return this.value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
