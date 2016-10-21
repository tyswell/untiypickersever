package com.tagtrade.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public @Bean File thaimtbFile() {
		try {
			File file = resourceLoader.getResource("classpath:thaimtb.html").getFile();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public @Bean FirebaseAuth firebaseAuth() {
		try {
			File file = resourceLoader.getResource("classpath:UnP.json").getFile();
			FirebaseOptions options = null;
			try {
				options = new FirebaseOptions.Builder()
				  .setServiceAccount(new FileInputStream(file))
				  .setDatabaseUrl("https://unity-picker.firebaseio.com/")
				  .build();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			FirebaseApp app = FirebaseApp.initializeApp(options);
			FirebaseAuth auth = FirebaseAuth.getInstance(app);
			
			return auth;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
