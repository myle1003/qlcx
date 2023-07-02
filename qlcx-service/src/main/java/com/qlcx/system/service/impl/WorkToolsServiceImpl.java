package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.WorkToolsMapper;
import com.qlcx.system.domain.WorkTools;
import com.qlcx.system.service.IWorkToolsService;
import com.qlcx.common.core.text.Convert;


@Service
public class WorkToolsServiceImpl implements IWorkToolsService 
{
    @Autowired
    private WorkToolsMapper workToolsMapper;


    @Override
    public WorkTools selectWorkToolsById(Long idWord)
    {
        return workToolsMapper.selectWorkToolsById(idWord);
    }

    /**
     * 查询 Danh sách
     * 
     * @param workTools  
     * @return  
     */
    @Override
    public List<WorkTools> selectWorkToolsList(WorkTools workTools)
    {
        return workToolsMapper.selectWorkToolsList(workTools);
    }

    @Override
    public int insertWorkTools(WorkTools workTools)
    {
        return workToolsMapper.insertWorkTools(workTools);
    }

    @Override
    public int updateWorkTools(WorkTools workTools)
    {
        return workToolsMapper.updateWorkTools(workTools);
    }

    @Override
    public int deleteWorkToolsByIds(String ids)
    {
        return workToolsMapper.deleteWorkToolsByIds(Convert.toStrArray(ids));
    }

    /**
     * Xóa 信息
     * 
     * @param idWord  ID
     * @return 结果
     */
    @Override
    public int deleteWorkToolsById(Long idWord)
    {
        return workToolsMapper.deleteWorkToolsById(idWord);
    }
}
