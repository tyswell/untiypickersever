package com.tagtrade.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.tagtrade.service.jersey.HealthController;
import com.tagtrade.service.jersey.account.AccountService;



@Profile("web")
@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(HealthController.class);
		register(AccountService.class);
	}
	
}
