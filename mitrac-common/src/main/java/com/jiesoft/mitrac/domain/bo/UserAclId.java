package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * UserAclId generated by hbm2java
 */
public class UserAclId implements java.io.Serializable {

	private String accountId;
	private String userId;
	private String aclId;

	public UserAclId() {
	}

	public UserAclId(String accountId, String userId, String aclId) {
		this.accountId = accountId;
		this.userId = userId;
		this.aclId = aclId;
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

	public String getAclId() {
		return this.aclId;
	}

	public void setAclId(String aclId) {
		this.aclId = aclId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserAclId))
			return false;
		UserAclId castOther = (UserAclId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& ((this.getAclId() == castOther.getAclId()) || (this
						.getAclId() != null && castOther.getAclId() != null && this
						.getAclId().equals(castOther.getAclId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getAclId() == null ? 0 : this.getAclId().hashCode());
		return result;
	}

}