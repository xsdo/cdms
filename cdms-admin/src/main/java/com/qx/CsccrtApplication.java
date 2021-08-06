package com.qx;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;


/**
 * 启动类
 * @author patient
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
public class CsccrtApplication {
	
	 public static void main(String[] args){
	        // System.setProperty("spring.devtools.restart.enabled", "false");
	        SpringApplication.run(CsccrtApplication.class, args);
	        System.out.println("(♥◠‿◠)ﾉﾞ  xxx训练系统启动成功   ლ(´ڡ`ლ)ﾞ  \n");
	 }
	@Bean
	public Filter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.addExposedHeader("x-auth-token");
		config.addExposedHeader("x-total-count");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
