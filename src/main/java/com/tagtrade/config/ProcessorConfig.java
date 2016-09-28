package com.tagtrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tagtrade.batch.processor.thaimtb.ThaimtbProcessor;

@Configuration
public class ProcessorConfig {
	
	@Bean
	public ThaimtbProcessor thaimtbProcessor() {
		return new ThaimtbProcessor();
	}

}
