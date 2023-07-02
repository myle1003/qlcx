package com.qlcx.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Program annotation configuration
*/
@Configuration
// indicates that the proxy object is exposed through the aop framework, and AopContext can be accessed
@EnableAspectJAutoProxy(exposeProxy = true)
// Specify the path of the Mapper class package to be scanned
@MapperScan("com.qlcx.**.mapper")
public class ApplicationConfig
{

}
