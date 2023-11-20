package com.crfstech.MyRemote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MyRemoteApplication implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");
		registry.
				addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("**/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");

	}
	public static void main(String[] args) {
		SpringApplication.run(MyRemoteApplication.class, args);

	}


}
