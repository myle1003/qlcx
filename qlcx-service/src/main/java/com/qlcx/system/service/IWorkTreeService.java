package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.WorkTree;


public interface IWorkTreeService 
{

    public WorkTree selectWorkTreeById(Long idWord);

    public List<WorkTree> selectWorkTreeList(WorkTree workTree);

    public int insertWorkTree(WorkTree workTree);

    public int updateWorkTree(WorkTree workTree);
  
    public int deleteWorkTreeByIds(String ids);

    /**
     * Xóa 信息
     * 
     * @param idWord  ID
     * @return 结果
     */
    public int deleteWorkTreeById(Long idWord);
}
