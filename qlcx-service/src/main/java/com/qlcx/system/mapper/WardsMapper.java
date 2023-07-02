package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.Wards;

public interface WardsMapper 
{

    public Wards selectWardsById(Long id);
 
    public List<Wards> selectWardsList(Wards wards);
 
    public int insertWards(Wards wards);
 
    public int updateWards(Wards wards);

    public int deleteWardsById(Long id);

    public int deleteWardsByIds(String[] ids);
}
