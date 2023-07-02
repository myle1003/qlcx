package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.Provinces;

public interface ProvincesMapper 
{

    public Provinces selectProvincesById(Long id);
 
    public List<Provinces> selectProvincesList(Provinces provinces);
 
    public int insertProvinces(Provinces provinces);
 
    public int updateProvinces(Provinces provinces);

    public int deleteProvincesById(Long id);

    public int deleteProvincesByIds(String[] ids);
}
