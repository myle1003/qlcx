package com.qlcx.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.system.domain.Wards;
import com.qlcx.system.mapper.WardsMapper;
import com.qlcx.system.service.IWardsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author apm
 * @date 2020-10-23
 */
@Service
public class WardsServiceImpl implements IWardsService 
{
    @Autowired
    private WardsMapper wardsMapper;


    @Override
    public Wards selectWardsById(Long id)
    {
        return wardsMapper.selectWardsById(id);
    }

    /**
     * Hỏi thăm【请填写功能名称】Cột表
     * 
     * @param wards 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Wards> selectWardsList(Wards wards)
    {
        return wardsMapper.selectWardsList(wards);
    }
 
    @Override
    public int insertWards(Wards wards)
    {
        return wardsMapper.insertWards(wards);
    }
 
    @Override
    public int updateWards(Wards wards)
    {
        return wardsMapper.updateWards(wards);
    }

    /**
     *xóa bỏ【请填写功能名称】对象
     * 
     * @param ids 需要xóa的数据ID
     * @return 结果
     */
    @Override
    public int deleteWardsByIds(String ids)
    {
        return wardsMapper.deleteWardsByIds(Convert.toStrArray(ids));
    }

    /**
     *xóa bỏ【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteWardsById(Long id)
    {
        return wardsMapper.deleteWardsById(id);
    }
}
