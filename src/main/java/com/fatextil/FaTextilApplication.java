package com.fatextil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FaTextilApplication {
	public static void main(String[] args) {SpringApplication.run(FaTextilApplication.class, args);}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(@NotNull CorsRegistry registry) {
//				registry.addMapping("/api/**").allowedOrigins("http://localhost:3000");
//			}
//		};
//	}

}

