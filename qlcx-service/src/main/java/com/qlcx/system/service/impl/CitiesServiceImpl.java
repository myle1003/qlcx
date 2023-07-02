package com.qlcx.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.system.domain.Cities;
import com.qlcx.system.mapper.CitiesMapper;
import com.qlcx.system.service.ICitiesService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author apm
 * @date 2020-10-23
 */
@Service
public class CitiesServiceImpl implements ICitiesService 
{
    @Autowired
    private CitiesMapper citiesMapper;


    @Override
    public Cities selectCitiesById(Long id)
    {
        return citiesMapper.selectCitiesById(id);
    }

    /**
     * Hỏi thăm【请填写功能名称】Cột表
     * 
     * @param cities 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Cities> selectCitiesList(Cities cities)
    {
        return citiesMapper.selectCitiesList(cities);
    }

    @Override
    public int insertCities(Cities cities)
    {
        return citiesMapper.insertCities(cities);
    }

    @Override
    public int updateCities(Cities cities)
    {
        return citiesMapper.updateCities(cities);
    }

    /**
     *xóa bỏ【请填写功能名称】对象
     * 
     * @param ids 需要xóa的数据ID
     * @return 结果
     */
    @Override
    public int deleteCitiesByIds(String ids)
    {
        return citiesMapper.deleteCitiesByIds(Convert.toStrArray(ids));
    }

    /**
     *xóa bỏ【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCitiesById(Long id)
    {
        return citiesMapper.deleteCitiesById(id);
    }
}
