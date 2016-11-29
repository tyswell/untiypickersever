package com.tagtrade.constant;

public class UserLoginType {
	
	public static final int FACEBOOK_LOGIN = 1;
	public static final int GOOGLE_LOGIN = 2;
	
	public static int convertUserLoginType(String provider) {
		if (provider != null) {
			if (provider.contains("facebook")) {
				return FACEBOOK_LOGIN;
			} else if (provider.contains("google")) {
				return GOOGLE_LOGIN;
			}
		}
		
		return 0;
	}

}
