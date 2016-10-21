package com.tagtrade.config;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.tagtrade.jersey.HealthController;
import com.tagtrade.jersey.user.UserFrontendService;



@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(HealthController.class);
		register(UserFrontendService.class);
	}
	
}
