package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.Cities;

public interface CitiesMapper 
{

    public Cities selectCitiesById(Long id);

    public List<Cities> selectCitiesList(Cities cities);

    public int insertCities(Cities cities);

    public int updateCities(Cities cities);

    public int deleteCitiesById(Long id);

    public int deleteCitiesByIds(String[] ids);
}
