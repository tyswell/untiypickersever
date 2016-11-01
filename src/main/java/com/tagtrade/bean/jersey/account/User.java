package com.tagtrade.bean.jersey.account;

/**
 * Created by deksen on 5/15/16 AD.
 */
public class User {

	private Integer userId;
	private String email;
	private String displayName;

	private String tokenUid;
	private String profileURL;
	private Integer userLoginType;
	private Device device;
	private FacebookUser facebookUser;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getTokenUid() {
		return tokenUid;
	}

	public void setTokenUid(String tokenUid) {
		this.tokenUid = tokenUid;
	}

	public String getProfileURL() {
		return profileURL;
	}

	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public FacebookUser getFacebookUser() {
		return facebookUser;
	}

	public void setFacebookUser(FacebookUser facebookUser) {
		this.facebookUser = facebookUser;
	}

	public Integer getUserLoginType() {
		return userLoginType;
	}

	public void setUserLoginType(Integer userLoginType) {
		this.userLoginType = userLoginType;
	}
}
