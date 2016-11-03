package com.tagtrade.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

@Configuration
@PropertySource("classpath:config.properties")
public class CommonConfig {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	private @Value("${firebase.database.url}") String firebaseDBUrl;
	
	public @Bean File thaimtbFile() {
		try {
			File file = resourceLoader.getResource("classpath:thaimtb.html").getFile();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public @Bean File serviceAccountFile() {
		try {
			File file = resourceLoader.getResource("classpath:UnP.json").getFile();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Bean
	public FirebaseAuth firebaseAuth() {
		try {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setServiceAccount(new FileInputStream(serviceAccountFile()))
					.setDatabaseUrl(firebaseDBUrl)
					.build();
			FirebaseApp app = FirebaseApp.initializeApp(options);
			FirebaseAuth auth = FirebaseAuth.getInstance(app); 
			return auth;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
