package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.TreeHistory;
 
public interface TreeHistoryMapper 
{
 
    public TreeHistory selectTreeHistoryById(Long id);
 
    public List<TreeHistory> selectTreeHistoryList(TreeHistory treeHistory);
 
    public int insertTreeHistory(TreeHistory treeHistory);
 
    public int updateTreeHistory(TreeHistory treeHistory);
  
    public int deleteTreeHistoryById(Long id);
  
    public int deleteTreeHistoryByIds(String[] ids);
}
