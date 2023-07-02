package com.qlcx.system.service;

import java.util.List;

import com.qlcx.system.domain.Wards;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author apm
 * @date 2020-10-23
 */
public interface IWardsService 
{

    public Wards selectWardsById(Long id);
 
    public List<Wards> selectWardsList(Wards wards);
 
    public int insertWards(Wards wards);
 
    public int updateWards(Wards wards);

    public int deleteWardsByIds(String ids);

    /**
     *xóa bỏ【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteWardsById(Long id);
}
