package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.Tools;


public interface IToolsService 
{
 
    public Tools selectToolsById(Long id);

    public List<Tools> selectToolsList(Tools tools);

    public int insertTools(Tools tools);

    public int updateTools(Tools tools);
  
    public int deleteToolsByIds(String ids);

    public int deleteToolsById(Long id);
}
