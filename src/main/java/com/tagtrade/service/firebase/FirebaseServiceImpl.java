package com.tagtrade.service.firebase;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.OnFailureListener;
import com.google.firebase.tasks.OnSuccessListener;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;
import com.tagtrade.bean.user.FirebaseFacebookUser;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.mapper.FirebaseMapper;

public class FirebaseServiceImpl implements FirebaseService {

	@Autowired
	private FirebaseAuth firebaseAuth;
	
	@Override
	public FirebaseUser getFirebaseUser(String tokenId) {
		Task<FirebaseToken> authTask = firebaseAuth.verifyIdToken(tokenId)
		.addOnSuccessListener(new OnSuccessListener<FirebaseToken>() {
	        @Override
	        public void onSuccess(FirebaseToken decodedToken) {
	        }
			
		})
		.addOnFailureListener(new OnFailureListener() {
			
			@Override
			public void onFailure(Exception e) {
				e.printStackTrace();
			}
		});
	    try {
	        Tasks.await(authTask);
	    } catch(ExecutionException | InterruptedException e ){
	        e.printStackTrace();
	        return null;
	    }
	    
	    FirebaseToken decodedToken = authTask.getResult();
		
		return FirebaseMapper.map(decodedToken);
	}

	@Override
	public FirebaseUser getFirebaseUserTest(String tokenId) {
		FirebaseUser result = new FirebaseUser();
		result.setEmail("tys_te@hotmail.com");
		result.setUserId("LNQT3FvvYXeJkYnPzww1ng9R5bf2");
		result.setDisplayName("Tys Naisen");
		result.setProvider("facebook.com");
		
		FirebaseFacebookUser fbUser = new FirebaseFacebookUser();
		fbUser.setFacebookId("10154907477446754");
		
		result.setFirebaseFacebookUser(fbUser);
		
		return result;
	}
	
}
