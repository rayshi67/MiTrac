package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * StatusCodeId generated by hbm2java
 */
public class StatusCodeId implements java.io.Serializable {

	private String accountId;
	private String deviceId;
	private int statusCode;

	public StatusCodeId() {
	}

	public StatusCodeId(String accountId, String deviceId, int statusCode) {
		this.accountId = accountId;
		this.deviceId = deviceId;
		this.statusCode = statusCode;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StatusCodeId))
			return false;
		StatusCodeId castOther = (StatusCodeId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getDeviceId() == castOther.getDeviceId()) || (this
						.getDeviceId() != null
						&& castOther.getDeviceId() != null && this
						.getDeviceId().equals(castOther.getDeviceId())))
				&& (this.getStatusCode() == castOther.getStatusCode());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getDeviceId() == null ? 0 : this.getDeviceId().hashCode());
		result = 37 * result + this.getStatusCode();
		return result;
	}

}
