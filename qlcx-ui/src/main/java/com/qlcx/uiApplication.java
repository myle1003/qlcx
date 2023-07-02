package com.qlcx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = "com.qlcx")
public class uiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(uiApplication.class, args);
	}

}
