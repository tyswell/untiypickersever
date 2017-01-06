package com.tagtrade.service.user;

import com.tagtrade.bean.jersey.account.Device;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.dataacess.entity.bean.EUser;

public interface UserService {
	
	public void registerUser(FirebaseUser user, Device device);
	
	public void login(FirebaseUser user, Device device);
	
	public boolean isUserValid(String userId);
	
	public boolean isUserIdExist(String userId);
		
	public boolean isEmailExist(String email);
	
	public void updateTokenFB(String userId, String tokenFB, String facebookId);
		
	public FirebaseUser getFirebaseUser(String tokenId);
	
	public EUser getUser(String userId);
	
	public void logout(String userId);

}
