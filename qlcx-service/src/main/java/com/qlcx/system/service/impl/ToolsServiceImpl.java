package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.ToolsMapper;
import com.qlcx.system.domain.Tools;
import com.qlcx.system.service.IToolsService;
import com.qlcx.common.core.text.Convert;

@Service
public class ToolsServiceImpl implements IToolsService 
{
    @Autowired
    private ToolsMapper toolsMapper;

 
    @Override
    public Tools selectToolsById(Long id)
    {
        return toolsMapper.selectToolsById(id);
    }

    @Override
    public List<Tools> selectToolsList(Tools tools)
    {
        return toolsMapper.selectToolsList(tools);
    }

    @Override
    public int insertTools(Tools tools)
    {
        return toolsMapper.insertTools(tools);
    }

    @Override
    public int updateTools(Tools tools)
    {
        return toolsMapper.updateTools(tools);
    }

    @Override
    public int deleteToolsByIds(String ids)
    {
        return toolsMapper.deleteToolsByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteToolsById(Long id)
    {
        return toolsMapper.deleteToolsById(id);
    }
}
