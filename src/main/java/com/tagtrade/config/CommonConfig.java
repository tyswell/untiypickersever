package com.tagtrade.config;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;

@Configuration
@PropertySource("classpath:config.properties")
public class CommonConfig {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public @Bean File thaimtbFile() {
		try {
			File file = resourceLoader.getResource("classpath:thaimtb.html").getFile();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
