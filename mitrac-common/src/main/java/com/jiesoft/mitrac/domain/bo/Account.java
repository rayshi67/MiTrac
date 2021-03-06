package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * Account generated by hbm2java
 */
public class Account implements java.io.Serializable {

	private String accountId;
	private Short accountType;
	private String notifyEmail;
	private Byte allowNotify;
	private Byte speedUnits;
	private Byte distanceUnits;
	private Byte volumeUnits;
	private Byte pressureUnits;
	private Byte economyUnits;
	private Byte temperatureUnits;
	private String currencyUnits;
	private Double fuelCostPerLiter;
	private Byte latLonFormat;
	private Byte geocoderMode;
	private String privateLabelName;
	private Byte isBorderCrossing;
	private Integer retainedEventAge;
	private Integer maximumDevices;
	private Short totalPingCount;
	private Short maxPingCount;
	private Byte autoAddDevices;
	private String dcsPropertiesId;
	private Byte smsEnabled;
	private String smsProperties;
	private String emailProperties;
	private Integer expirationTime;
	private Byte allowWebService;
	private String defaultUser;
	private String password;
	private String contactName;
	private String contactPhone;
	private String contactEmail;
	private String timeZone;
	private Integer passwdChangeTime;
	private Integer passwdQueryTime;
	private Integer lastLoginTime;
	private Byte isActive;
	private String displayName;
	private String description;
	private String notes;
	private Integer lastUpdateTime;
	private Integer creationTime;

	public Account() {
	}

	public Account(String accountId) {
		this.accountId = accountId;
	}

	public Account(String accountId, Short accountType, String notifyEmail,
			Byte allowNotify, Byte speedUnits, Byte distanceUnits,
			Byte volumeUnits, Byte pressureUnits, Byte economyUnits,
			Byte temperatureUnits, String currencyUnits,
			Double fuelCostPerLiter, Byte latLonFormat, Byte geocoderMode,
			String privateLabelName, Byte isBorderCrossing,
			Integer retainedEventAge, Integer maximumDevices,
			Short totalPingCount, Short maxPingCount, Byte autoAddDevices,
			String dcsPropertiesId, Byte smsEnabled, String smsProperties,
			String emailProperties, Integer expirationTime,
			Byte allowWebService, String defaultUser, String password,
			String contactName, String contactPhone, String contactEmail,
			String timeZone, Integer passwdChangeTime, Integer passwdQueryTime,
			Integer lastLoginTime, Byte isActive, String displayName,
			String description, String notes, Integer lastUpdateTime,
			Integer creationTime) {
		this.accountId = accountId;
		this.accountType = accountType;
		this.notifyEmail = notifyEmail;
		this.allowNotify = allowNotify;
		this.speedUnits = speedUnits;
		this.distanceUnits = distanceUnits;
		this.volumeUnits = volumeUnits;
		this.pressureUnits = pressureUnits;
		this.economyUnits = economyUnits;
		this.temperatureUnits = temperatureUnits;
		this.currencyUnits = currencyUnits;
		this.fuelCostPerLiter = fuelCostPerLiter;
		this.latLonFormat = latLonFormat;
		this.geocoderMode = geocoderMode;
		this.privateLabelName = privateLabelName;
		this.isBorderCrossing = isBorderCrossing;
		this.retainedEventAge = retainedEventAge;
		this.maximumDevices = maximumDevices;
		this.totalPingCount = totalPingCount;
		this.maxPingCount = maxPingCount;
		this.autoAddDevices = autoAddDevices;
		this.dcsPropertiesId = dcsPropertiesId;
		this.smsEnabled = smsEnabled;
		this.smsProperties = smsProperties;
		this.emailProperties = emailProperties;
		this.expirationTime = expirationTime;
		this.allowWebService = allowWebService;
		this.defaultUser = defaultUser;
		this.password = password;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.timeZone = timeZone;
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

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Short getAccountType() {
		return this.accountType;
	}

	public void setAccountType(Short accountType) {
		this.accountType = accountType;
	}

	public String getNotifyEmail() {
		return this.notifyEmail;
	}

	public void setNotifyEmail(String notifyEmail) {
		this.notifyEmail = notifyEmail;
	}

	public Byte getAllowNotify() {
		return this.allowNotify;
	}

	public void setAllowNotify(Byte allowNotify) {
		this.allowNotify = allowNotify;
	}

	public Byte getSpeedUnits() {
		return this.speedUnits;
	}

	public void setSpeedUnits(Byte speedUnits) {
		this.speedUnits = speedUnits;
	}

	public Byte getDistanceUnits() {
		return this.distanceUnits;
	}

	public void setDistanceUnits(Byte distanceUnits) {
		this.distanceUnits = distanceUnits;
	}

	public Byte getVolumeUnits() {
		return this.volumeUnits;
	}

	public void setVolumeUnits(Byte volumeUnits) {
		this.volumeUnits = volumeUnits;
	}

	public Byte getPressureUnits() {
		return this.pressureUnits;
	}

	public void setPressureUnits(Byte pressureUnits) {
		this.pressureUnits = pressureUnits;
	}

	public Byte getEconomyUnits() {
		return this.economyUnits;
	}

	public void setEconomyUnits(Byte economyUnits) {
		this.economyUnits = economyUnits;
	}

	public Byte getTemperatureUnits() {
		return this.temperatureUnits;
	}

	public void setTemperatureUnits(Byte temperatureUnits) {
		this.temperatureUnits = temperatureUnits;
	}

	public String getCurrencyUnits() {
		return this.currencyUnits;
	}

	public void setCurrencyUnits(String currencyUnits) {
		this.currencyUnits = currencyUnits;
	}

	public Double getFuelCostPerLiter() {
		return this.fuelCostPerLiter;
	}

	public void setFuelCostPerLiter(Double fuelCostPerLiter) {
		this.fuelCostPerLiter = fuelCostPerLiter;
	}

	public Byte getLatLonFormat() {
		return this.latLonFormat;
	}

	public void setLatLonFormat(Byte latLonFormat) {
		this.latLonFormat = latLonFormat;
	}

	public Byte getGeocoderMode() {
		return this.geocoderMode;
	}

	public void setGeocoderMode(Byte geocoderMode) {
		this.geocoderMode = geocoderMode;
	}

	public String getPrivateLabelName() {
		return this.privateLabelName;
	}

	public void setPrivateLabelName(String privateLabelName) {
		this.privateLabelName = privateLabelName;
	}

	public Byte getIsBorderCrossing() {
		return this.isBorderCrossing;
	}

	public void setIsBorderCrossing(Byte isBorderCrossing) {
		this.isBorderCrossing = isBorderCrossing;
	}

	public Integer getRetainedEventAge() {
		return this.retainedEventAge;
	}

	public void setRetainedEventAge(Integer retainedEventAge) {
		this.retainedEventAge = retainedEventAge;
	}

	public Integer getMaximumDevices() {
		return this.maximumDevices;
	}

	public void setMaximumDevices(Integer maximumDevices) {
		this.maximumDevices = maximumDevices;
	}

	public Short getTotalPingCount() {
		return this.totalPingCount;
	}

	public void setTotalPingCount(Short totalPingCount) {
		this.totalPingCount = totalPingCount;
	}

	public Short getMaxPingCount() {
		return this.maxPingCount;
	}

	public void setMaxPingCount(Short maxPingCount) {
		this.maxPingCount = maxPingCount;
	}

	public Byte getAutoAddDevices() {
		return this.autoAddDevices;
	}

	public void setAutoAddDevices(Byte autoAddDevices) {
		this.autoAddDevices = autoAddDevices;
	}

	public String getDcsPropertiesId() {
		return this.dcsPropertiesId;
	}

	public void setDcsPropertiesId(String dcsPropertiesId) {
		this.dcsPropertiesId = dcsPropertiesId;
	}

	public Byte getSmsEnabled() {
		return this.smsEnabled;
	}

	public void setSmsEnabled(Byte smsEnabled) {
		this.smsEnabled = smsEnabled;
	}

	public String getSmsProperties() {
		return this.smsProperties;
	}

	public void setSmsProperties(String smsProperties) {
		this.smsProperties = smsProperties;
	}

	public String getEmailProperties() {
		return this.emailProperties;
	}

	public void setEmailProperties(String emailProperties) {
		this.emailProperties = emailProperties;
	}

	public Integer getExpirationTime() {
		return this.expirationTime;
	}

	public void setExpirationTime(Integer expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Byte getAllowWebService() {
		return this.allowWebService;
	}

	public void setAllowWebService(Byte allowWebService) {
		this.allowWebService = allowWebService;
	}

	public String getDefaultUser() {
		return this.defaultUser;
	}

	public void setDefaultUser(String defaultUser) {
		this.defaultUser = defaultUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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
