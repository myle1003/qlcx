package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.WorkTreeMapper;
import com.qlcx.system.domain.WorkTree;
import com.qlcx.system.service.IWorkTreeService;
import com.qlcx.common.core.text.Convert;

@Service
public class WorkTreeServiceImpl implements IWorkTreeService 
{
    @Autowired
    private WorkTreeMapper workTreeMapper;


    @Override
    public WorkTree selectWorkTreeById(Long idWord)
    {
        return workTreeMapper.selectWorkTreeById(idWord);
    }

    /**
     * 查询 Danh sách
     * 
     * @param workTree  
     * @return  
     */
    @Override
    public List<WorkTree> selectWorkTreeList(WorkTree workTree)
    {
        return workTreeMapper.selectWorkTreeList(workTree);
    }

    @Override
    public int insertWorkTree(WorkTree workTree)
    {
        return workTreeMapper.insertWorkTree(workTree);
    }

    @Override
    public int updateWorkTree(WorkTree workTree)
    {
        return workTreeMapper.updateWorkTree(workTree);
    }

    @Override
    public int deleteWorkTreeByIds(String ids)
    {
        return workTreeMapper.deleteWorkTreeByIds(Convert.toStrArray(ids));
    }

    /**
     * Xóa 信息
     * 
     * @param idWord  ID
     * @return 结果
     */
    @Override
    public int deleteWorkTreeById(Long idWord)
    {
        return workTreeMapper.deleteWorkTreeById(idWord);
    }
}
