package com.tagtrade.mapper;

import com.tagtrade.bean.jersey.account.User;
import com.tagtrade.bean.jersey.account.UserLogin;
import com.tagtrade.dataacess.entity.bean.EUser;
import com.tagtrade.dataacess.entity.bean.EUserDevice;
import com.tagtrade.dataacess.entity.bean.EUserFacebook;
import com.tagtrade.util.dozer.MappingUtil;

public class UserMapper {

	public static EUser toEntity(User data) {
		if (data != null) {
			EUser result = new EUser();
			
			MappingUtil.map(data, result);

			return result;
		} else {
			return null;
		}
	}
	
	public static UserLogin toJersey(User data) {
		if (data != null) {
			UserLogin result = new UserLogin();
			
			MappingUtil.map(data, result);

			return result;
		} else {
			return null;
		}
	}


	
	public static EUserDevice toEntityDevice(User data) {
		if (data != null) {
			EUserDevice result = new EUserDevice();
			MappingUtil.map(data.getDevice(), result);
			result.setUserId(data.getUserId());

			return result;
		} else {
			return null;
		}
	}
	
	public static EUserFacebook toEntityFacebook(User data) {
		if (data != null) {
			EUserFacebook result = new EUserFacebook();
			result.setUserId(data.getUserId());
			result.setFacebookId(data.getFacebookUser().getFacebookId());
			result.setTokenFacebook(data.getFacebookUser().getTokenFacebook());

			return result;
		} else {
			return null;
		}
	}

}
