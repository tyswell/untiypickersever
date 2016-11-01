package com.tagtrade.service.user;

import com.tagtrade.bean.jersey.account.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User login(User user);
		
	public boolean isEmailExist(String email);
	
	public void updateTokenFB(int userId, String tokenFB, String facebookId);

	public boolean isValidTokenUID(int userId, String tokenUID);
	
	public boolean isValidToken(User user);

}
