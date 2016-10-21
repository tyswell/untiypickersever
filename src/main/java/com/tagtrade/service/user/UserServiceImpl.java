package com.tagtrade.service.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.firebase.auth.FirebaseToken;
import com.tagtrade.bean.jersey.account.User;
import com.tagtrade.constant.StatusConst;
import com.tagtrade.constant.UserLoginType;
import com.tagtrade.dataacess.entity.bean.EUser;
import com.tagtrade.dataacess.entity.bean.EUserDevice;
import com.tagtrade.dataacess.entity.bean.EUserFacebook;
import com.tagtrade.dataacess.entity.dao.EUserDAO;
import com.tagtrade.dataacess.entity.dao.EUserDeviceDAO;
import com.tagtrade.dataacess.entity.dao.EUserFacebookDAO;
import com.tagtrade.mapper.UserMapper;
import com.tagtrade.service.firebase.FirebaseService;

public class UserServiceImpl implements UserService {

	@Autowired
	private EUserDAO eUserDAO;
	@Autowired
	private EUserFacebookDAO eUserFacebookDAO;
	@Autowired
	private EUserDeviceDAO eUserDeviceDAO;
	
	@Autowired
	private FirebaseService firebaseService;
	
	@Override
	public void registerUser(User user) {
		//-------- INSERT EUSER
		EUser eUser = UserMapper.toEntity(user);
		eUser.setActive(StatusConst.ACTIVE);
		eUserDAO.insert(eUser);
		
		if (user.getUserLoginType() == UserLoginType.FACEBOOK_LOGIN) {
			//-------- INSERT EUSER_FACEBOOK
			EUserFacebook eUserFacebook = UserMapper.toEntityFacebook(user);
			eUserFacebookDAO.insert(eUserFacebook);
		}

		//-------- INSERT EUSER_DEVICE
		EUserDevice eUserDevice = UserMapper.toEntityDevice(user);
		eUserDevice.setActive(StatusConst.ACTIVE);
		eUserDeviceDAO.insert(eUserDevice);
	}
	
	@Override
	public void login(User user) {
		boolean isDeviceExist = eUserDeviceDAO.isKeyExist(user.getUsername(), user.getDevice().getDeviceModel());
		//ADD OTHER DEVICE
		if (!isDeviceExist) {
			//-------- INSERT EUSER_DEVICE
			EUserDevice eUserDevice = UserMapper.toEntityDevice(user);
			eUserDevice.setActive(StatusConst.ACTIVE);
			eUserDeviceDAO.insert(eUserDevice);
		}
		
		// UPDATE TOKEN
		updateTokenFB(user.getUsername(), user.getFacebookUser().getTokenFacebook());
	}

	@Override
	public boolean isValidTokenUID(String username, String tokenUID) {
		EUser eUserData =eUserDAO.selectByKey(username);
		if (eUserData != null) {
			if (eUserData.getTokenUid().equals(tokenUID)) {
				return true;
			} 
		} 
		
		FirebaseToken firebaseData = firebaseService.getFirebaseToken(tokenUID);
		if (firebaseData != null) {
			String firebaseTokenUID = null/*firebaseData.getUid()*/;
			if (firebaseTokenUID != null) {
				if (eUserData != null) {
					eUserData.setTokenUid(firebaseTokenUID);
					eUserDAO.updateByKey(eUserData);
				}
				
				if (firebaseTokenUID.equals(tokenUID)) {
					return true;
				} 
			}
		}
		
		return false;
	}

	@Override
	public boolean isUserExist(String username) {
		return eUserDAO.isKeyExist(username);
	}
	
	@Override
	public void updateTokenFB(String username, String tokenFB) {
		EUserFacebook eUserFacebook = eUserFacebookDAO.selectByKey(username);
		// UPDATE TOKEN
		if (eUserFacebook != null) {
			if (!eUserFacebook.getTokenFacebook().equals(tokenFB)){
				eUserFacebook.setTokenFacebook(tokenFB);
				eUserFacebookDAO.updateByKey(eUserFacebook);
			}
		} else { // OLD LOGIN WITH GOOGLE, NOW LOGIN WITH FB
			//-------- INSERT EUSER_FACEBOOK
			EUserFacebook dataUserFacebook = new EUserFacebook();
			dataUserFacebook.setUsername(username);
			dataUserFacebook.setTokenFacebook(tokenFB);
			eUserFacebookDAO.insert(dataUserFacebook);
		}
	}
	
	

}
