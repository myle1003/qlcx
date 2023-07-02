package com.qlcx.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.system.service.ISysConfigService;

/**
 * qlcx pioneered html call thymeleaf to realize parameter management
*/
@Service("config")
public class ConfigService
{
    @Autowired
    private ISysConfigService configService;

    /**
    * Query parameter configuration information based on key name
    *
    * @param configKey parameter key name
    * @return parameter key value
    */
    public String getKey(String configKey)
    {
        return configService.selectConfigByKey(configKey);
    }
}
