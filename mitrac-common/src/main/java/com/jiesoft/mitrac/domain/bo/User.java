package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private UserId id;
	private Short userType;
	private String roleId;
	private String password;
	private Byte gender;
	private String notifyEmail;
	private String contactName;
	private String contactPhone;
	private String contactEmail;
	private String timeZone;
	private String firstLoginPageId;
	private String preferredDeviceId;
	private Short maxAccessLevel;
	private Integer passwdChangeTime;
	private Integer passwdQueryTime;
	private Integer lastLoginTime;
	private Byte isActive;
	private String displayName;
	private String description;
	private String notes;
	private Integer lastUpdateTime;
	private Integer creationTime;

	public User() {
	}

	public User(UserId id) {
		this.id = id;
	}

	public User(UserId id, Short userType, String roleId, String password,
			Byte gender, String notifyEmail, String contactName,
			String contactPhone, String contactEmail, String timeZone,
			String firstLoginPageId, String preferredDeviceId,
			Short maxAccessLevel, Integer passwdChangeTime,
			Integer passwdQueryTime, Integer lastLoginTime, Byte isActive,
			String displayName, String description, String notes,
			Integer lastUpdateTime, Integer creationTime) {
		this.id = id;
		this.userType = userType;
		this.roleId = roleId;
		this.password = password;
		this.gender = gender;
		this.notifyEmail = notifyEmail;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.timeZone = timeZone;
		this.firstLoginPageId = firstLoginPageId;
		this.preferredDeviceId = preferredDeviceId;
		this.maxAccessLevel = maxAccessLevel;
		this.passwdChangeTime = passwdChangeTime;
		this.passwdQueryTime = passwdQueryTime;
		this.lastLoginTime = lastLoginTime;
		this.isActive = isActive;
		this.displayName = displayName;
		this.description = description;
		this.notes = notes;
		this.lastUpdateTime = lastUpdateTime;
		this.creationTime = creationTime;
	}

	public UserId getId() {
		return this.id;
	}

	public void setId(UserId id) {
		this.id = id;
	}

	public Short getUserType() {
		return this.userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte getGender() {
		return this.gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getNotifyEmail() {
		return this.notifyEmail;
	}

	public void setNotifyEmail(String notifyEmail) {
		this.notifyEmail = notifyEmail;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getFirstLoginPageId() {
		return this.firstLoginPageId;
	}

	public void setFirstLoginPageId(String firstLoginPageId) {
		this.firstLoginPageId = firstLoginPageId;
	}

	public String getPreferredDeviceId() {
		return this.preferredDeviceId;
	}

	public void setPreferredDeviceId(String preferredDeviceId) {
		this.preferredDeviceId = preferredDeviceId;
	}

	public Short getMaxAccessLevel() {
		return this.maxAccessLevel;
	}

	public void setMaxAccessLevel(Short maxAccessLevel) {
		this.maxAccessLevel = maxAccessLevel;
	}

	public Integer getPasswdChangeTime() {
		return this.passwdChangeTime;
	}

	public void setPasswdChangeTime(Integer passwdChangeTime) {
		this.passwdChangeTime = passwdChangeTime;
	}

	public Integer getPasswdQueryTime() {
		return this.passwdQueryTime;
	}

	public void setPasswdQueryTime(Integer passwdQueryTime) {
		this.passwdQueryTime = passwdQueryTime;
	}

	public Integer getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Integer lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
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

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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