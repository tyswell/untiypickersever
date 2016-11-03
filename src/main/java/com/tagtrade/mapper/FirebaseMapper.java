package com.tagtrade.mapper;

import com.google.firebase.auth.FirebaseToken;
import com.tagtrade.bean.user.FirebaseFacebookUser;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.constant.UserLoginType;
import com.tagtrade.dataacess.entity.bean.EUser;

public class FirebaseMapper {
	
	public static FirebaseUser map(FirebaseToken data) {
		if (data == null) {
			return null;
		}
		
		FirebaseUser result = new FirebaseUser();
		result.setEmail(data.getEmail());
		result.setUserId(data.getUid());
		result.setDisplayName(data.getName());
		result.setProvider(data.getClaims().get("sign_in_provider").toString());
		
		FirebaseFacebookUser fbUser = new FirebaseFacebookUser();
		fbUser.setFacebookId(data.getClaims().get("facebook.com").toString());
		
		result.setFirebaseFacebookUser(fbUser);
		
		return result;
	}
	
	public static EUser mapToDB(FirebaseUser data) {
		if (data == null) {
			return null;
		}
		
		EUser result = new EUser();
		result.setEmail(data.getEmail());
		result.setUserId(data.getUserId());
		result.setUserLoginType(UserLoginType.FACEBOOK_LOGIN);
		result.setDisplayName(data.getDisplayName());
		
		return result;
	}

}
