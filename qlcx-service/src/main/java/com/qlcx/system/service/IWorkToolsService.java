package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.WorkTools;

/**
 *  Service接口
 * 
 * @author qlcx
 * @date 2021-02-24
 */
public interface IWorkToolsService 
{

    public WorkTools selectWorkToolsById(Long idWord);

    public List<WorkTools> selectWorkToolsList(WorkTools workTools);

    public int insertWorkTools(WorkTools workTools);

    public int updateWorkTools(WorkTools workTools);
  
    public int deleteWorkToolsByIds(String ids);

    /**
     * Xóa 信息
     * 
     * @param idWord  ID
     * @return 结果
     */
    public int deleteWorkToolsById(Long idWord);
}
