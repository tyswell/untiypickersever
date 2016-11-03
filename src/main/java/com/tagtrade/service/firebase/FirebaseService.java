package com.tagtrade.service.firebase;

import com.tagtrade.bean.user.FirebaseUser;

public interface FirebaseService {
	
	public FirebaseUser getFirebaseUser(String tokenId);
	
	public FirebaseUser getFirebaseUserTest(String tokenId);

}
