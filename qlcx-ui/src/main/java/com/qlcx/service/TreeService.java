package com.qlcx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.domain.Tree;
import com.qlcx.mapper.TreeMapper;

@Service
public class TreeService
{
    @Autowired
    private TreeMapper treeMapper;
    public List<Tree> selectTreeList(Tree tree)
    {
        return treeMapper.selectTreeList(tree);
    }
    
    public List<Tree> selectTreeListMap(String trees)
    {
        return treeMapper.selectTreeListMap(Convert.toStrArray(trees));
    }
}
