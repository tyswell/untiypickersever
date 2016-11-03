package com.tagtrade.mapper;

import com.tagtrade.bean.jersey.account.Device;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.dataacess.entity.bean.EUserDevice;
import com.tagtrade.dataacess.entity.bean.EUserFacebook;
import com.tagtrade.util.dozer.MappingUtil;

public class UserMapper {

	public static EUserDevice toEntityDevice(FirebaseUser data, Device device) {
		if (data != null) {
			EUserDevice result = new EUserDevice();
			MappingUtil.map(device, result);
			result.setUserId(data.getUserId());

			return result;
		} else {
			return null;
		}
	}
	
	public static EUserFacebook toEntityFacebook(FirebaseUser data) {
		if (data != null) {
			EUserFacebook result = new EUserFacebook();
			result.setUserId(data.getUserId());
			result.setFacebookId(data.getFirebaseFacebookUser().getFacebookId());
			result.setTokenFacebook(data.getFirebaseFacebookUser().getFacebookToken());

			return result;
		} else {
			return null;
		}
	}

}
