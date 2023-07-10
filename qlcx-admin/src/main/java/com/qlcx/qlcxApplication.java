package com.qlcx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class qlcxApplication
{
    public static void main(String[] args)
    {
//        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(qlcxApplication.class, args);
    }
}