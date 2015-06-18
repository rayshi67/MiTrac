package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * AccountStringId generated by hbm2java
 */
public class AccountStringId implements java.io.Serializable {

	private String accountId;
	private String stringId;

	public AccountStringId() {
	}

	public AccountStringId(String accountId, String stringId) {
		this.accountId = accountId;
		this.stringId = stringId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getStringId() {
		return this.stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AccountStringId))
			return false;
		AccountStringId castOther = (AccountStringId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getStringId() == castOther.getStringId()) || (this
						.getStringId() != null
						&& castOther.getStringId() != null && this
						.getStringId().equals(castOther.getStringId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getStringId() == null ? 0 : this.getStringId().hashCode());
		return result;
	}

}