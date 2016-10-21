package com.tagtrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tagtrade.service.content.ContentService;
import com.tagtrade.service.content.ContentServiceImpl;
import com.tagtrade.service.firebase.FirebaseService;
import com.tagtrade.service.firebase.FirebaseServiceImpl;
import com.tagtrade.service.mobile.MobileService;
import com.tagtrade.service.mobile.MobileServiceImpl;
import com.tagtrade.service.searching.SearchingService;
import com.tagtrade.service.searching.SearchingServiceImpl;
import com.tagtrade.service.url.FacebookGroupService;
import com.tagtrade.service.url.FacebookGroupServiceImpl;
import com.tagtrade.service.user.UserService;
import com.tagtrade.service.user.UserServiceImpl;

@Configuration
public class ServiceConfig {
	
	@Bean
	public ContentService thaimtbContentService() {
		return new ContentServiceImpl();
	}
	
	@Bean
	public SearchingService searchingService() {
		return new SearchingServiceImpl();
	}
	
	@Bean
	public MobileService mobileService() {
		return new MobileServiceImpl();
	}
	
	@Bean
	public FacebookGroupService facebookGroupService() {
		return new FacebookGroupServiceImpl();
	}
	
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public FirebaseService firebaseService() {
		return new FirebaseServiceImpl();
	}
}
