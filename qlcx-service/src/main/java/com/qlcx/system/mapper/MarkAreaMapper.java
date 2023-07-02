package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.MarkArea;

public interface MarkAreaMapper 
{
 
    public MarkArea selectMarkAreaById(Long id);

    public List<MarkArea> selectMarkAreaList(MarkArea markArea);

    public int insertMarkArea(MarkArea markArea);

    public int updateMarkArea(MarkArea markArea);
  
    public int deleteMarkAreaById(Long id);
  
    public int deleteMarkAreaByIds(String[] ids);
}
