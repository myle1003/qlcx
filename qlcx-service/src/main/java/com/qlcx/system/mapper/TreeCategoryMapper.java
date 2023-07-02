package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.TreeCategory;

public interface TreeCategoryMapper 
{
    public TreeCategory selectTreeCategoryById(Long id);
    public List<TreeCategory> selectTreeCategoryList(TreeCategory treeCategory);
    public int insertTreeCategory(TreeCategory treeCategory);
    public int updateTreeCategory(TreeCategory treeCategory);
    public int deleteTreeCategoryById(Long id);
    public int deleteTreeCategoryByIds(String[] ids);
}
