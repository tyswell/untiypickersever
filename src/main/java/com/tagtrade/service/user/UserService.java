package com.tagtrade.service.user;

import com.tagtrade.bean.jersey.account.User;

public interface UserService {
	
	public void registerUser(User user);
	
	public void login(User user);
	
	public boolean isValidTokenUID(String username, String tokenUID);
	
	public boolean isUserExist(String username);
	
	public void updateTokenFB(String username, String tokenFB);

}
