package com.qlcx.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.system.domain.AttributeTreeArea;
import com.qlcx.system.domain.TreeCategory;
import com.qlcx.system.mapper.AttributeTreeAreaMapper;
import com.qlcx.system.mapper.TreeCategoryMapper;
import com.qlcx.system.service.ITreeCategoryService;

@Service
public class TreeCategoryServiceImpl implements ITreeCategoryService 
{
    @Autowired 
    private TreeCategoryMapper treeCategoryMapper; 

    @Autowired
    private AttributeTreeAreaMapper attributeTreeAreaMapper;

    @Override
    public TreeCategory selectTreeCategoryById(Long id)
    {
        return treeCategoryMapper.selectTreeCategoryById(id);
    }

    @Override
    public List<TreeCategory> selectTreeCategoryList(TreeCategory treeCategory)
    {
        return treeCategoryMapper.selectTreeCategoryList(treeCategory);
    }

    @Override
    public int insertTreeCategory(TreeCategory treeCategory,String[] listAttribute)
    {
        int idTreeCategory = treeCategoryMapper.insertTreeCategory(treeCategory);
        if(listAttribute!=null)
        {
        	for(int i=0;i<listAttribute.length;i++){
            	AttributeTreeArea attributeTreeArea = new AttributeTreeArea();
            	attributeTreeArea.setIdAttribute(Long.parseLong(listAttribute[i]));
            	attributeTreeArea.setIdCategory(treeCategory.getId());
            	attributeTreeAreaMapper.insertAttributeTreeArea(attributeTreeArea);
            }
        }
        return idTreeCategory;
    }

    @Override
    public int updateTreeCategory(TreeCategory treeCategory,String[] listAttribute)
    {

        int idTreeCategory = treeCategoryMapper.updateTreeCategory(treeCategory);
        attributeTreeAreaMapper.deleteAttributeTreeAreaByIdCategory(treeCategory.getId());
        if(listAttribute!=null)
        {
        	for(int i=0;i<listAttribute.length;i++){
            	AttributeTreeArea attributeTreeArea = new AttributeTreeArea();
            	attributeTreeArea.setIdAttribute(Long.parseLong(listAttribute[i]));
            	attributeTreeArea.setIdCategory(treeCategory.getId());
            	attributeTreeAreaMapper.insertAttributeTreeArea(attributeTreeArea);
            }
        }
        return idTreeCategory;
    }

    @Override
    public int deleteTreeCategoryByIds(String ids)
    {
        return treeCategoryMapper.deleteTreeCategoryByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteTreeCategoryById(Long id)
    {
        return treeCategoryMapper.deleteTreeCategoryById(id);
    }
}
