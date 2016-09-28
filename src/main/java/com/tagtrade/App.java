package com.tagtrade;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App extends SpringBootServletInitializer
{
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	/* TEST */
    public static void main( String[] args )
    {    	
    	SpringApplication.run(App.class, args);
    }
    

}
