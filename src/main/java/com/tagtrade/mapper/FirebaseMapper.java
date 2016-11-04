package com.tagtrade.mapper;

import java.util.ArrayList;
import java.util.List;

import com.google.api.client.util.ArrayMap;
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
		
	    ArrayMap<String, Object> firebaseMap = (ArrayMap<String, Object>)data.getClaims().get("firebase");
		result.setProvider(firebaseMap.get("sign_in_provider").toString());
		
		FirebaseFacebookUser fbUser = new FirebaseFacebookUser();
		ArrayMap<String, Object> idenMap = (ArrayMap<String, Object>)firebaseMap.get("identities");
		List<String> insideDatas = (ArrayList<String>)idenMap.get(result.getProvider());
		for (String insideData : insideDatas) {
			fbUser.setFacebookId(insideData);
		}
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
