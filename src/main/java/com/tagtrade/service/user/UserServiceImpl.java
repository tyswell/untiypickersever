package com.tagtrade.service.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.eaio.uuid.UUID;
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
import com.tagtrade.service.firebase.FacebookService;
import com.tagtrade.util.DateUtil;

public class UserServiceImpl implements UserService {

	@Autowired
	private EUserDAO eUserDAO;
	@Autowired
	private EUserFacebookDAO eUserFacebookDAO;
	@Autowired
	private EUserDeviceDAO eUserDeviceDAO;
	@Autowired
	private FacebookService facebookService;
	
	@Override
	public User registerUser(User user) {
		//-------- INSERT EUSER
		String tokenUID = genUIDToken();
		int userId = getUserId();
		EUser eUser = UserMapper.toEntity(user);
		eUser.setUserId(userId);
		eUser.setTokenUid(tokenUID);
		eUser.setTokenUidExpireDate((java.sql.Date) genTokenUidExpireDate());
		eUser.setActive(StatusConst.ACTIVE);
		eUserDAO.insert(eUser);
		
		user.setUserId(userId);
		user.setTokenUid(tokenUID);
		
		if (user.getUserLoginType() == UserLoginType.FACEBOOK_LOGIN) {
			//-------- INSERT EUSER_FACEBOOK
			EUserFacebook eUserFacebook = UserMapper.toEntityFacebook(user);
			eUserFacebookDAO.insert(eUserFacebook);
		}

		//-------- INSERT EUSER_DEVICE
		EUserDevice eUserDevice = UserMapper.toEntityDevice(user);
		eUserDevice.setActive(StatusConst.ACTIVE);
		eUserDeviceDAO.insert(eUserDevice);
		
		return user;
	}
	
	@Override
	public User login(User user) {
		String tokenUID = genUIDToken(); 
		EUser eUser = eUserDAO.selectByEmail(user.getEmail());
		eUser.setTokenUid(tokenUID);
		eUser.setTokenUidExpireDate((java.sql.Date) genTokenUidExpireDate());
		eUser.setActive(StatusConst.ACTIVE);
		eUserDAO.updateByKey(eUser);
		
		user.setUserId(eUser.getUserId());
		user.setTokenUid(tokenUID);
		
		EUserDevice eUserDeviceData = eUserDeviceDAO.selectByKey(user.getUserId(), user.getDevice().getDeviceModel());
		
		if (eUserDeviceData != null) {
			//-------- UPDATE TOKEN DEVICE
			if (!eUserDeviceData.getTokenNotification().equals(user.getDevice().getTokenNotification())) {
				eUserDeviceData.setTokenNotification(user.getDevice().getTokenNotification());
				eUserDeviceDAO.updateByKey(eUserDeviceData);
			}
		} else {
			//-------- INSERT EUSER_DEVICE
			EUserDevice eUserDevice = UserMapper.toEntityDevice(user);
			eUserDevice.setActive(StatusConst.ACTIVE);
			eUserDeviceDAO.insert(eUserDevice);
		}
				
		// UPDATE TOKEN
		updateTokenFB(user.getUserId(), user.getFacebookUser().getTokenFacebook(), user.getFacebookUser().getFacebookId());
		
		return user;
	}

	@Override
	public boolean isValidTokenUID(int userId, String tokenUID) {
		EUser eUserData = eUserDAO.selectByKey(userId);
		if (eUserData != null) {
			if (eUserData.getTokenUid().equals(tokenUID)) {
				if (!DateUtil.isDateOverDue(eUserData.getTokenUidExpireDate(), DateUtil.getNow())) {
					return true;
				} else {
					eUserData.setTokenUid(tokenUID);
					eUserData.setTokenUidExpireDate((java.sql.Date) genTokenUidExpireDate());
					eUserDAO.updateByKey(eUserData);
					return false;
				}
			} 
		} 
		
		return false;
	}

	@Override
	public boolean isEmailExist(String email) {
		return eUserDAO.isEmailExist(email);
	}
	
	@Override
	public void updateTokenFB(int userId, String tokenFB, String facebookId) {
		EUserFacebook eUserFacebook = eUserFacebookDAO.selectByKey(userId);
		// UPDATE TOKEN
		if (eUserFacebook != null) {
			if (!eUserFacebook.getTokenFacebook().equals(tokenFB)){
				eUserFacebook.setTokenFacebook(tokenFB);
				eUserFacebookDAO.updateByKey(eUserFacebook);
			}
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
	public boolean isValidToken(User user) {
		Integer userLoginType = user.getUserLoginType();
		String email = user.getEmail();
		if (UserLoginType.FACEBOOK_LOGIN == userLoginType) {
			return facebookService.isValidToken(email, user.getFacebookUser().getTokenFacebook());
		} else if (UserLoginType.GOOGLE_LOGIN == userLoginType) {
			return false;
		}
		
		return false;
	}
	
	private String genUIDToken() {
		UUID u = new UUID();
		return u.toString();
	}
	
	private java.sql.Date genTokenUidExpireDate() {
		Date now = DateUtil.getNow();
		return new java.sql.Date(DateUtil.addMonth(now, 3).getTime());
	}
	
	private Integer getUserId() {
		int x = eUserDAO.selectMaxId();
		return Integer.valueOf(x + 1);
	}
	

}
