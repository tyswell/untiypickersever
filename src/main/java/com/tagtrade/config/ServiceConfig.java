package com.tagtrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tagtrade.service.content.ThaimtbContentService;
import com.tagtrade.service.content.ThaimtbContentServiceImpl;
import com.tagtrade.service.mobile.MobileService;
import com.tagtrade.service.mobile.MobileServiceImpl;
import com.tagtrade.service.searching.SearchingService;
import com.tagtrade.service.searching.SearchingServiceImpl;

@Configuration
public class ServiceConfig {
	
	@Bean
	public ThaimtbContentService thaimtbContentService() {
		return new ThaimtbContentServiceImpl();
	}
	
	@Bean
	public SearchingService searchingService() {
		return new SearchingServiceImpl();
	}
	
	@Bean
	public MobileService mobileService() {
		return new MobileServiceImpl();
	}

}
