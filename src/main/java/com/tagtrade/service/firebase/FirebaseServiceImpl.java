package com.tagtrade.service.firebase;


import org.springframework.beans.factory.annotation.Autowired;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
public class FirebaseServiceImpl implements FirebaseService {
	
	@Autowired
	private FirebaseAuth firebaseAuth;
	
	public FirebaseToken getFirebaseToken(String tokenUID) {

		
		return null;
	}

}
