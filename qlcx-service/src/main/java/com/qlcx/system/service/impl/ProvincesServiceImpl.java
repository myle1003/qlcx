package com.qlcx.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.system.domain.Provinces;
import com.qlcx.system.mapper.ProvincesMapper;
import com.qlcx.system.service.IProvincesService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author apm
 * @date 2020-10-23
 */
@Service
public class ProvincesServiceImpl implements IProvincesService 
{
    @Autowired
    private ProvincesMapper provincesMapper;


    @Override
    public Provinces selectProvincesById(Long id)
    {
        return provincesMapper.selectProvincesById(id);
    }

    /**
     * Hỏi thăm【请填写功能名称】Cột表
     * 
     * @param provinces 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Provinces> selectProvincesList(Provinces provinces)
    {
        return provincesMapper.selectProvincesList(provinces);
    }
 
    @Override
    public int insertProvinces(Provinces provinces)
    {
        return provincesMapper.insertProvinces(provinces);
    }
 
    @Override
    public int updateProvinces(Provinces provinces)
    {
        return provincesMapper.updateProvinces(provinces);
    }

    /**
     *xóa bỏ【请填写功能名称】对象
     * 
     * @param ids 需要xóa的数据ID
     * @return 结果
     */
    @Override
    public int deleteProvincesByIds(String ids)
    {
        return provincesMapper.deleteProvincesByIds(Convert.toStrArray(ids));
    }

    /**
     *xóa bỏ【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteProvincesById(Long id)
    {
        return provincesMapper.deleteProvincesById(id);
    }
}
