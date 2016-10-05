package com.tagtrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tagtrade.service.content.ContentService;
import com.tagtrade.service.content.ContentServiceImpl;
import com.tagtrade.service.mobile.MobileService;
import com.tagtrade.service.mobile.MobileServiceImpl;
import com.tagtrade.service.searching.SearchingService;
import com.tagtrade.service.searching.SearchingServiceImpl;
import com.tagtrade.service.url.FacebookGroupService;
import com.tagtrade.service.url.FacebookGroupServiceImpl;

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

}
