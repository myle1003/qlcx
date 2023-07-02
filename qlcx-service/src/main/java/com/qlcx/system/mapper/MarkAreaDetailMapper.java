package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.MarkAreaDetail;

public interface MarkAreaDetailMapper 
{
 
    public MarkAreaDetail selectMarkAreaDetailById(Long id);

    public List<MarkAreaDetail> selectMarkAreaDetailList(MarkAreaDetail markAreaDetail);

    public int insertMarkAreaDetail(MarkAreaDetail markAreaDetail);

    public int updateMarkAreaDetail(MarkAreaDetail markAreaDetail);
  
    public int deleteMarkAreaDetailById(Long id);
    
    public int deleteMarkAreaDetailByIdParent(Long id);
  
    public int deleteMarkAreaDetailByIds(String[] ids);
}
