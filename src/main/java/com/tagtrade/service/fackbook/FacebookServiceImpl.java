package com.tagtrade.service.fackbook;


import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;
import com.tagtrade.facebook.DefaultFacebookGraphClient;

public class FacebookServiceImpl implements FacebookService {
	
	static String host = "192.168.5.24";
	static int port = 3128;
	
	public boolean isValidToken(String email, String tokenFB) {
		 DefaultFacebookClient df = new DefaultFacebookGraphClient(tokenFB, 50000, host, port, Version.VERSION_2_7);
		 
		 try {
			 User fbUser = df.fetchObject("me", 
					 User.class,
					 Parameter.with("fields", "email"));
			 if (fbUser == null) {
				 return false;
			 }
			 
			 if (fbUser.getEmail().equals(email)) {
				 return true;
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 return false;
		 }
		 
		 return false;
		 
	}

}
