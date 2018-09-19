package com.inspur.viz.user.data;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String password;
	private String salt;
	private String accountStatus;
	private String isSys;
	private String createTime;
	private String lockTime;
	private String expiredTime;
	private String pwdUptTime;
	private String pwdTime;
	private List<RoleInfo> roleList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLockTime() {
		return lockTime;
	}

	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}

	public String getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getPwdUptTime() {
		return pwdUptTime;
	}

	public void setPwdUptTime(String pwdUptTime) {
		this.pwdUptTime = pwdUptTime;
	}

	public String getPwdTime() {
		return pwdTime;
	}

	public void setPwdTime(String pwdTime) {
		this.pwdTime = pwdTime;
	}

	public String getCredentialsSalt() {
		return this.userName + "1";
	}

	public List<RoleInfo> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleInfo> roleList) {
		this.roleList = roleList;
	}

}
