package com.tagtrade.bean.user;

public class FirebaseUser {
	
	private String userId;
	private String displayName;
	private String provider;
	private String email;
	
	private FirebaseFacebookUser firebaseFacebookUser;

	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FirebaseFacebookUser getFirebaseFacebookUser() {
		return firebaseFacebookUser;
	}

	public void setFirebaseFacebookUser(FirebaseFacebookUser firebaseFacebookUser) {
		this.firebaseFacebookUser = firebaseFacebookUser;
	}
	
	

}
