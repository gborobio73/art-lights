package com.leeloo.lights.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000", "chrome-extension://fdmmgilgnpjigdojojpjoooidkmcomcm")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
//			.allowedHeaders("header1", "header2", "header3")
//			.exposedHeaders("header1", "header2")
			.allowCredentials(true).maxAge(3600);
	}
}

