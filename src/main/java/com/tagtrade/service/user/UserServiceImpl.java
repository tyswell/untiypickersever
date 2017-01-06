package com.tagtrade.service.user;


import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.bean.jersey.account.Device;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.constant.StatusConst;
import com.tagtrade.constant.UserLoginType;
import com.tagtrade.dataacess.entity.bean.EUser;
import com.tagtrade.dataacess.entity.bean.EUserDevice;
import com.tagtrade.dataacess.entity.bean.EUserFacebook;
import com.tagtrade.dataacess.entity.bean.EUserGoogle;
import com.tagtrade.dataacess.entity.dao.EUserDAO;
import com.tagtrade.dataacess.entity.dao.EUserDeviceDAO;
import com.tagtrade.dataacess.entity.dao.EUserFacebookDAO;
import com.tagtrade.dataacess.entity.dao.EUserGoogleDAO;
import com.tagtrade.mapper.FirebaseMapper;
import com.tagtrade.mapper.UserMapper;
import com.tagtrade.service.fackbook.FacebookService;
import com.tagtrade.service.firebase.FirebaseService;

public class UserServiceImpl implements UserService {

	@Autowired
	private EUserDAO eUserDAO;
	@Autowired
	private EUserFacebookDAO eUserFacebookDAO;
	@Autowired
	private EUserGoogleDAO eUserGoogleDAO;
	@Autowired
	private EUserDeviceDAO eUserDeviceDAO;
	@Autowired
	private FacebookService facebookService;
	@Autowired
	private FirebaseService firebaseService;
	
	@Override
	public void registerUser(FirebaseUser user, Device device) {
		//-------- INSERT EUSER
		EUser eUser = FirebaseMapper.mapToDB(user);
		eUser.setActive(StatusConst.ACTIVE);
		eUser.setLogin(StatusConst.ACTIVE);
		eUserDAO.insert(eUser);
				
		if (eUser.getUserLoginType() == UserLoginType.FACEBOOK_LOGIN) {
			//-------- INSERT EUSER_FACEBOOK
			EUserFacebook eUserFacebook = UserMapper.toEntityFacebook(user);
			eUserFacebookDAO.insert(eUserFacebook);
		} else if (eUser.getUserLoginType() == UserLoginType.GOOGLE_LOGIN) {
			EUserGoogle eUserGoogle = UserMapper.toEntityGoogle(user);
			eUserGoogleDAO.insert(eUserGoogle);
		}

		//-------- INSERT EUSER_DEVICE
		EUserDevice eUserDevice = UserMapper.toEntityDevice(user, device);
		eUserDevice.setActive(StatusConst.ACTIVE);
		eUserDeviceDAO.insert(eUserDevice);
	}
	
	@Override
	public void login(FirebaseUser user, Device device) {		
		eUserDAO.setStatusLogin(user.getUserId(), StatusConst.ACTIVE);
		
		EUserDevice eUserDeviceData = eUserDeviceDAO.selectByKey(user.getUserId(), device.getDeviceModel());
		
		if (eUserDeviceData != null) {
			//-------- UPDATE TOKEN DEVICE
			if (!eUserDeviceData.getTokenNotification().equals(device.getTokenNotification())) {
				eUserDeviceData.setTokenNotification(device.getTokenNotification());
				eUserDeviceDAO.updateByKey(eUserDeviceData);
			}
		} else {
			//-------- INSERT EUSER_DEVICE
			EUserDevice eUserDevice = UserMapper.toEntityDevice(user, device);
			eUserDevice.setActive(StatusConst.ACTIVE);
			eUserDeviceDAO.insert(eUserDevice);
		}
				
		// UPDATE TOKEN
		if (UserLoginType.FACEBOOK_LOGIN == user.getUserLoginType()) {
			updateTokenFB(user.getUserId(), user.getFirebaseFacebookUser().getFacebookToken(), user.getFirebaseFacebookUser().getFacebookId());
		}
	}
	

	@Override
	public boolean isUserValid(String userId) {
		EUser eUser = eUserDAO.selectByKey(userId);
		if (eUser.getActive().equals(StatusConst.ACTIVE)) {
			return true;
		}
		
		return false;
	}


	@Override
	public boolean isEmailExist(String email) {
		return eUserDAO.isEmailExist(email);
	}
	
	@Override
	public void updateTokenFB(String userId, String tokenFB, String facebookId) {
		EUserFacebook eUserFacebook = eUserFacebookDAO.selectByKey(userId);
		// UPDATE TOKEN
		if (eUserFacebook != null) {
			eUserFacebook.setTokenFacebook(tokenFB);
			eUserFacebookDAO.updateByKey(eUserFacebook);
		} else { // OLD LOGIN WITH GOOGLE, NOW LOGIN WITH FB
			//-------- INSERT EUSER_FACEBOOK
			EUserFacebook dataUserFacebook = new EUserFacebook();
			dataUserFacebook.setFacebookId(facebookId);
			dataUserFacebook.setUserId(userId);
			dataUserFacebook.setTokenFacebook(tokenFB);
			eUserFacebookDAO.insert(dataUserFacebook);
		}
	}

	@Override
	public FirebaseUser getFirebaseUser(String tokenId) {
//TODO	return firebaseService.getFirebaseUser(tokenId);
		return firebaseService.getFirebaseUserTest(tokenId);
	}
	
	@Override
	public boolean isUserIdExist(String userId) {
		return eUserDAO.isKeyExist(userId);
	}

	@Override
	public EUser getUser(String userId) {
		return eUserDAO.selectByKey(userId);
	}

	@Override
	public void logout(String userId) {
		eUserDAO.setStatusLogin(userId, StatusConst.INACTIVE);
	}

}
