package com.qlcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.qlcx.domain.Tree;


@Mapper
public interface TreeMapper 
{
    
    public Tree selectTreeById(Long id);

    public List<Tree> selectTreeList(Tree tree);
    
    public List<Tree> selectTreeListMap(String[] trees);


}
