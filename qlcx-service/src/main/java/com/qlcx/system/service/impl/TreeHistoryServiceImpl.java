package com.qlcx.system.service.impl;

import java.util.List;
import com.qlcx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.TreeHistoryMapper;
import com.qlcx.system.domain.TreeHistory;
import com.qlcx.system.service.ITreeHistoryService;
import com.qlcx.common.core.text.Convert;

/**
 *  Service业务层处理
 * 
 * @author qlcx
 * @date 2021-03-23
 */
@Service
public class TreeHistoryServiceImpl implements ITreeHistoryService 
{
    @Autowired
    private TreeHistoryMapper treeHistoryMapper;

 
    @Override
    public TreeHistory selectTreeHistoryById(Long id)
    {
        return treeHistoryMapper.selectTreeHistoryById(id);
    }

    /**
     * 查询 Danh sách
     * 
     * @param treeHistory  
     * @return  
     */
    @Override
    public List<TreeHistory> selectTreeHistoryList(TreeHistory treeHistory)
    {
        return treeHistoryMapper.selectTreeHistoryList(treeHistory);
    }
 
    @Override
    public int insertTreeHistory(TreeHistory treeHistory)
    {
        treeHistory.setCreateTime(DateUtils.getNowDate());
        return treeHistoryMapper.insertTreeHistory(treeHistory);
    }
 
    @Override
    public int updateTreeHistory(TreeHistory treeHistory)
    {
        return treeHistoryMapper.updateTreeHistory(treeHistory);
    }

    @Override
    public int deleteTreeHistoryByIds(String ids)
    {
        return treeHistoryMapper.deleteTreeHistoryByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteTreeHistoryById(Long id)
    {
        return treeHistoryMapper.deleteTreeHistoryById(id);
    }
}
