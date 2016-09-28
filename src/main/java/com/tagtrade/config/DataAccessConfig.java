package com.tagtrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tagtrade.dataacess.entity.dao.ESearchingDAO;
import com.tagtrade.dataacess.entity.dao.EThaimtbContentDAO;
import com.tagtrade.dataacess.entity.dao.EUserDAO;
import com.tagtrade.dataacess.entity.dao.ErSeachingMapThaimtbDAO;

@Configuration
public class DataAccessConfig {
	
	@Bean
	public ErSeachingMapThaimtbDAO erSeachingMapThaimtbDAO() {
		return new ErSeachingMapThaimtbDAO();
	}
	
	@Bean
	public ESearchingDAO eSearchingDAO() {
		return new ESearchingDAO();
	}

	@Bean
	public EThaimtbContentDAO eThaimtbContentDAO() {
		return new EThaimtbContentDAO();
	}
	
	@Bean
	public EUserDAO eUserDAO() {
		return new EUserDAO();
	}
}
