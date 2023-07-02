package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.Tools;
 
public interface ToolsMapper 
{
 
    public Tools selectToolsById(Long id);

    public List<Tools> selectToolsList(Tools tools);

    public int insertTools(Tools tools);

    public int updateTools(Tools tools);
  
    public int deleteToolsById(Long id);
  
    public int deleteToolsByIds(String[] ids);
}
