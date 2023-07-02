package com.qlcx.system.service;

import java.util.List;

import com.qlcx.system.domain.TreeCategory;

public interface ITreeCategoryService 
{
    public TreeCategory selectTreeCategoryById(Long id);
    public List<TreeCategory> selectTreeCategoryList(TreeCategory treeCategory);
    public int insertTreeCategory(TreeCategory treeCategory,String[] listAttribute);
    public int updateTreeCategory(TreeCategory treeCategory,String[] listAttribute);
    public int deleteTreeCategoryByIds(String ids);
    public int deleteTreeCategoryById(Long id);
}
