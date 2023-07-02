package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.TreeHistory;

/**
 *  Service接口
 * 
 * @author qlcx
 * @date 2021-03-23
 */
public interface ITreeHistoryService 
{
 
    public TreeHistory selectTreeHistoryById(Long id);
 
    public List<TreeHistory> selectTreeHistoryList(TreeHistory treeHistory);
 
    public int insertTreeHistory(TreeHistory treeHistory);
 
    public int updateTreeHistory(TreeHistory treeHistory);
  
    public int deleteTreeHistoryByIds(String ids);

    public int deleteTreeHistoryById(Long id);
}
