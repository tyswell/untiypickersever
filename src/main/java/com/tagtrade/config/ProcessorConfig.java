package com.tagtrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tagtrade.batch.processor.FacebookProcessor;
import com.tagtrade.batch.processor.thaimtb.ThaimtbProcessor;
import com.tagtrade.batch.processor.webpra.WebpraProcessor;

@Configuration
public class ProcessorConfig {
	
	@Bean
	public ThaimtbProcessor thaimtbProcessor() {
		return new ThaimtbProcessor();
	}
	
	@Bean
	public FacebookProcessor facebookProcessor() {
		return new FacebookProcessor();
	}
	
	@Bean
	public WebpraProcessor webpraProcessor() {
		return new WebpraProcessor();
	}

}
