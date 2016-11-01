package com.tagtrade.bean.jersey.account;

public class UserLogin {
	
	private Integer userId;
	private String tokenUid;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTokenUid() {
		return tokenUid;
	}
	public void setTokenUid(String tokenUid) {
		this.tokenUid = tokenUid;
	}
	
}
