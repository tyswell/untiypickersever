package com.tagtrade.config;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.tagtrade.jersey.HealthController;
import com.tagtrade.jersey.device.DeviceFrontendService;
import com.tagtrade.jersey.search.SearchingFrontendService;
import com.tagtrade.jersey.user.UserFrontendService;



@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("com.tagtrade");
		register(HealthController.class);
		register(UserFrontendService.class);
		register(SearchingFrontendService.class);
		register(DeviceFrontendService.class);
	}
	
}
