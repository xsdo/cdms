package com.qx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/**
 * 启动类
 * @author patient
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CsccrtApplication {
	
	 public static void main(String[] args){
	        // System.setProperty("spring.devtools.restart.enabled", "false");
	        SpringApplication.run(CsccrtApplication.class, args);
	        System.out.println("(♥◠‿◠)ﾉﾞ  装备库系统-api启动成功   ლ(´ڡ`ლ)ﾞ  \n");
	 }
}
