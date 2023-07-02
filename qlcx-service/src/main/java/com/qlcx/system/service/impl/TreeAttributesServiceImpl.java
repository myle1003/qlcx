package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.TreeAttributesMapper;
import com.qlcx.system.domain.TreeAttributes;
import com.qlcx.system.service.ITreeAttributesService;
import com.qlcx.common.core.text.Convert;

/**
 *  Service业务层处理
 * 
 * @author qlcx
 * @date 2021-04-15
 */
@Service
public class TreeAttributesServiceImpl implements ITreeAttributesService 
{
    @Autowired
    private TreeAttributesMapper treeAttributesMapper;

 
    @Override
    public TreeAttributes selectTreeAttributesById(Long id)
    {
        return treeAttributesMapper.selectTreeAttributesById(id);
    }

    /**
     * 查询 Danh sách
     * 
     * @param treeAttributes  
     * @return  
     */
    @Override
    public List<TreeAttributes> selectTreeAttributesList(TreeAttributes treeAttributes)
    {
        return treeAttributesMapper.selectTreeAttributesList(treeAttributes);
    }
 
    @Override
    public int insertTreeAttributes(TreeAttributes treeAttributes)
    {
        return treeAttributesMapper.insertTreeAttributes(treeAttributes);
    }
 
    @Override
    public int updateTreeAttributes(TreeAttributes treeAttributes)
    {
        return treeAttributesMapper.updateTreeAttributes(treeAttributes);
    }

    @Override
    public int deleteTreeAttributesByIds(String ids)
    {
        return treeAttributesMapper.deleteTreeAttributesByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteTreeAttributesById(Long id)
    {
        return treeAttributesMapper.deleteTreeAttributesById(id);
    }
}
