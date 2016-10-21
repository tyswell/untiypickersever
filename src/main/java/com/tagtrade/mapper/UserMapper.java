package com.tagtrade.mapper;

import com.tagtrade.bean.jersey.account.Device;
import com.tagtrade.bean.jersey.account.User;
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
	
	public static EUserDevice toEntity(Device data) {
		if (data != null) {
			EUserDevice result = new EUserDevice();
			
			MappingUtil.map(data, result);

			return result;
		} else {
			return null;
		}
	}
	
	public static EUserDevice toEntityDevice(User data) {
		if (data != null) {
			EUserDevice result = toEntity(data.getDevice());
			result.setUsername(data.getUsername());

			return result;
		} else {
			return null;
		}
	}
	
	public static EUserFacebook toEntityFacebook(User data) {
		if (data != null) {
			EUserFacebook result = new EUserFacebook();
			result.setUsername(data.getUsername());
			result.setTokenFacebook(data.getFacebookUser().getTokenFacebook());

			return result;
		} else {
			return null;
		}
	}

}
