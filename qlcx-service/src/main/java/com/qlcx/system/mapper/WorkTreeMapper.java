package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.WorkTree;
 
public interface WorkTreeMapper 
{
    public WorkTree selectWorkTreeById(Long idWord);

    public List<WorkTree> selectWorkTreeList(WorkTree workTree);

    public int insertWorkTree(WorkTree workTree);

    public int updateWorkTree(WorkTree workTree);

    public int deleteWorkTreeById(Long idWord);

    public int deleteWorkTreeByIds(String[] idWords);
}
