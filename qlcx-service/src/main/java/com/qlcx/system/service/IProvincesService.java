package com.qlcx.system.service;

import java.util.List;

import com.qlcx.system.domain.Provinces;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author apm
 * @date 2020-10-23
 */
public interface IProvincesService 
{

    public Provinces selectProvincesById(Long id);
 
    public List<Provinces> selectProvincesList(Provinces provinces);
 
    public int insertProvinces(Provinces provinces);
 
    public int updateProvinces(Provinces provinces);

    public int deleteProvincesByIds(String ids);

    /**
     *xóa bỏ【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteProvincesById(Long id);
}
