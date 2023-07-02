package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.MarkArea;

/**
 *  Service接口
 * 
 * @author qlcx
 * @date 2022-03-19
 */
public interface IMarkAreaService 
{
 
    public MarkArea selectMarkAreaById(Long id);

    public List<MarkArea> selectMarkAreaList(MarkArea markArea);

    public int insertMarkArea(MarkArea markArea);

    public int updateMarkArea(MarkArea markArea);
  
    public int deleteMarkAreaByIds(String ids);

    public int deleteMarkAreaById(Long id);
}
