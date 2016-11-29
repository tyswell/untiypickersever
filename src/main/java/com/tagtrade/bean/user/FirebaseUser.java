package com.tagtrade.bean.user;

public class FirebaseUser {
	
	private String userId;
	private String displayName;
	private Integer userLoginType;
	private String email;
	
	private FirebaseFacebookUser firebaseFacebookUser;
	private FirebaseGoogleUser firebaseGoogleUser;

	
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

	public Integer getUserLoginType() {
		return userLoginType;
	}

	public void setUserLoginType(Integer userLoginType) {
		this.userLoginType = userLoginType;
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

	public FirebaseGoogleUser getFirebaseGoogleUser() {
		return firebaseGoogleUser;
	}

	public void setFirebaseGoogleUser(FirebaseGoogleUser firebaseGoogleUser) {
		this.firebaseGoogleUser = firebaseGoogleUser;
	}
	
	

}
