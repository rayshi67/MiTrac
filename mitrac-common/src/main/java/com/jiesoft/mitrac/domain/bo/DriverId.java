package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * DriverId generated by hbm2java
 */
public class DriverId implements java.io.Serializable {

	private String accountId;
	private String driverId;

	public DriverId() {
	}

	public DriverId(String accountId, String driverId) {
		this.accountId = accountId;
		this.driverId = driverId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getDriverId() {
		return this.driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DriverId))
			return false;
		DriverId castOther = (DriverId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getDriverId() == castOther.getDriverId()) || (this
						.getDriverId() != null
						&& castOther.getDriverId() != null && this
						.getDriverId().equals(castOther.getDriverId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getDriverId() == null ? 0 : this.getDriverId().hashCode());
		return result;
	}

}
