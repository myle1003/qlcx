package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.TreeAttributes;

/**
 *  Service接口
 * 
 * @author qlcx
 * @date 2021-04-15
 */
public interface ITreeAttributesService 
{
 
    public TreeAttributes selectTreeAttributesById(Long id);
 
    public List<TreeAttributes> selectTreeAttributesList(TreeAttributes treeAttributes);
 
    public int insertTreeAttributes(TreeAttributes treeAttributes);
 
    public int updateTreeAttributes(TreeAttributes treeAttributes);
  
    public int deleteTreeAttributesByIds(String ids);

    public int deleteTreeAttributesById(Long id);
}
