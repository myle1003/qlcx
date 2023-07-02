package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.Work;

public interface WorkMapper 
{
 
    public Work selectWorkById(Long id);

    public List<Work> selectWorkList(Work work);

    public int insertWork(Work work);

    public int updateWork(Work work);
  
    public int deleteWorkById(Long id);
  
    public int deleteWorkByIds(String[] ids);
}
