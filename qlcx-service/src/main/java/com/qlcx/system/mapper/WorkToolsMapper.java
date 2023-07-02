package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.WorkTools;
 
public interface WorkToolsMapper 
{

    public WorkTools selectWorkToolsById(Long idWord);

    public List<WorkTools> selectWorkToolsList(WorkTools workTools);

    public int insertWorkTools(WorkTools workTools);

    public int updateWorkTools(WorkTools workTools);

    public int deleteWorkToolsById(Long idWord);

    public int deleteWorkToolsByIds(String[] idWords);
}
