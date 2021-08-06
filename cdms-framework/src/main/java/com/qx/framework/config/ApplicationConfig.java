package com.qx.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 程序注解配置
 *
 * @author suhp
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.qx.**.mapper")
public class ApplicationConfig
{

}
