package com.tagtrade.bean.jersey.account;

/**
 * Created by deksen on 5/15/16 AD.
 */
public class User {

	private String username;
	private String displayName;

	private String tokenUid;
	private String profileURL;
	private Integer userLoginType;
	private Device device;
	private FacebookUser facebookUser;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
