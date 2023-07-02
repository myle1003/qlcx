package com.qlcx.system.service;

import java.util.List;

import com.qlcx.system.domain.Cities;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author apm
 * @date 2020-10-23
 */
public interface ICitiesService 
{

    public Cities selectCitiesById(Long id);

    public List<Cities> selectCitiesList(Cities cities);

    public int insertCities(Cities cities);

    public int updateCities(Cities cities);

    public int deleteCitiesByIds(String ids);

    /**
     *xóa bỏ【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCitiesById(Long id);
}
