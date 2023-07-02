package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.TreeAttributes;

public interface TreeAttributesMapper 
{
 
    public TreeAttributes selectTreeAttributesById(Long id);
 
    public List<TreeAttributes> selectTreeAttributesList(TreeAttributes treeAttributes);
 
    public int insertTreeAttributes(TreeAttributes treeAttributes);
 
    public int updateTreeAttributes(TreeAttributes treeAttributes);
  
    public int deleteTreeAttributesById(Long id);
  
    public int deleteTreeAttributesByIds(String[] ids);
}
