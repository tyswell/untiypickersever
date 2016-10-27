package com.tagtrade.service.user;

import com.tagtrade.bean.jersey.account.User;
import com.tagtrade.dataacess.entity.bean.EUser;

public interface UserService {
	
	public EUser getUser(String username);
	
	public String registerUser(User user);
	
	public String login(User user);
		
	public boolean isUserExist(String username);
	
	public void updateTokenFB(String username, String tokenFB);

	public boolean isValidTokenUID(String username, String tokenUID);
	
	public boolean isValidToken(User user);

}
