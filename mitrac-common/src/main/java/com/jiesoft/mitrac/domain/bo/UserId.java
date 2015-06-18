package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * UserId generated by hbm2java
 */
public class UserId implements java.io.Serializable {

	private String accountId;
	private String userId;

	public UserId() {
	}

	public UserId(String accountId, String userId) {
		this.accountId = accountId;
		this.userId = userId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserId))
			return false;
		UserId castOther = (UserId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}