package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.AttributeTreeAreaMapper;
import com.qlcx.system.domain.AttributeTreeArea;
import com.qlcx.system.service.IAttributeTreeAreaService;
import com.qlcx.common.core.text.Convert;

/**
 *  Service业务层处理
 * 
 * @author qlcx
 * @date 2021-04-15
 */
@Service
public class AttributeTreeAreaServiceImpl implements IAttributeTreeAreaService 
{
    @Autowired
    private AttributeTreeAreaMapper attributeTreeAreaMapper;


    @Override
    public AttributeTreeArea selectAttributeTreeAreaById(Long idTree)
    {
        return attributeTreeAreaMapper.selectAttributeTreeAreaById(idTree);
    }

    /**
     * 查询 Danh sách
     * 
     * @param attributeTreeArea  
     * @return  
     */
    @Override
    public List<AttributeTreeArea> selectAttributeTreeAreaList(AttributeTreeArea attributeTreeArea)
    {
        return attributeTreeAreaMapper.selectAttributeTreeAreaList(attributeTreeArea);
    }

    @Override
    public int insertAttributeTreeArea(AttributeTreeArea attributeTreeArea)
    {
        return attributeTreeAreaMapper.insertAttributeTreeArea(attributeTreeArea);
    }

    @Override
    public int updateAttributeTreeArea(AttributeTreeArea attributeTreeArea)
    {
        return attributeTreeAreaMapper.updateAttributeTreeArea(attributeTreeArea);
    }

    @Override
    public int deleteAttributeTreeAreaByIds(String ids)
    {
        return attributeTreeAreaMapper.deleteAttributeTreeAreaByIds(Convert.toStrArray(ids));
    }

    /**
     * Xóa 信息
     * 
     * @param idTree  ID
     * @return 结果
     */
    @Override
    public int deleteAttributeTreeAreaById(Long idTree)
    {
        return attributeTreeAreaMapper.deleteAttributeTreeAreaByIdTree(idTree);
    }
}
