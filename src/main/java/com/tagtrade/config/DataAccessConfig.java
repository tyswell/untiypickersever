package com.tagtrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tagtrade.dataacess.entity.dao.EContentDAO;
import com.tagtrade.dataacess.entity.dao.ESearchingDAO;
import com.tagtrade.dataacess.entity.dao.EUserDAO;
import com.tagtrade.dataacess.entity.dao.EUserDeviceDAO;
import com.tagtrade.dataacess.entity.dao.EUserFacebookDAO;
import com.tagtrade.dataacess.entity.dao.ErSeachingMapContentDAO;
import com.tagtrade.dataacess.entity.dao.ErSearchTypeMapWebTypeDAO;
import com.tagtrade.dataacess.entity.dao.RFacebookGroupDAO;
import com.tagtrade.dataacess.entity.dao.SContentDAO;

@Configuration
public class DataAccessConfig {
	
	@Bean
	public ErSearchTypeMapWebTypeDAO erSearchTypeMapWebTypeDAO() {
		return new ErSearchTypeMapWebTypeDAO();
	}
	
	@Bean
	public ErSeachingMapContentDAO erSeachingMapContentDAO() {
		return new ErSeachingMapContentDAO();
	}
	
	@Bean
	public ESearchingDAO eSearchingDAO() {
		return new ESearchingDAO();
	}

	@Bean
	public EContentDAO eContentDAO() {
		return new EContentDAO();
	}
	
	@Bean
	public EUserDAO eUserDAO() {
		return new EUserDAO();
	}
	
	@Bean
	public EUserFacebookDAO eUserFacebookDAO() {
		return new EUserFacebookDAO();
	}
	
	@Bean
	public EUserDeviceDAO eUserDeviceDAO() {
		return new EUserDeviceDAO();
	}
	
	@Bean
	public RFacebookGroupDAO rFacebookGroupDAO() {
		return new RFacebookGroupDAO();
	}
	
	@Bean
	public SContentDAO sContentDAO() {
		return new SContentDAO();
	}
	

}
