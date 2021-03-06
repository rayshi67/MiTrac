package com.jiesoft.mitrac.domain.bo;

// Generated 18/06/2015 4:40:44 PM by Hibernate Tools 4.3.1

/**
 * RoleAclId generated by hbm2java
 */
public class RoleAclId implements java.io.Serializable {

	private String accountId;
	private String roleId;
	private String aclId;

	public RoleAclId() {
	}

	public RoleAclId(String accountId, String roleId, String aclId) {
		this.accountId = accountId;
		this.roleId = roleId;
		this.aclId = aclId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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
		if (!(other instanceof RoleAclId))
			return false;
		RoleAclId castOther = (RoleAclId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null && castOther.getRoleId() != null && this
						.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getAclId() == castOther.getAclId()) || (this
						.getAclId() != null && castOther.getAclId() != null && this
						.getAclId().equals(castOther.getAclId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getAclId() == null ? 0 : this.getAclId().hashCode());
		return result;
	}

}
