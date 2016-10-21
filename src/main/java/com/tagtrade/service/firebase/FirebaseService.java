package com.tagtrade.service.firebase;

import com.google.firebase.auth.FirebaseToken;

public interface FirebaseService {
	
	public FirebaseToken getFirebaseToken(String tokenUID); 

}
