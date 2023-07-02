package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.AttributeTreeArea;

/**
 *  Service接口
 * 
 * @author qlcx
 * @date 2021-04-15
 */
public interface IAttributeTreeAreaService 
{

    public AttributeTreeArea selectAttributeTreeAreaById(Long idTree);

    public List<AttributeTreeArea> selectAttributeTreeAreaList(AttributeTreeArea attributeTreeArea);

    public int insertAttributeTreeArea(AttributeTreeArea attributeTreeArea);

    public int updateAttributeTreeArea(AttributeTreeArea attributeTreeArea);
  
    public int deleteAttributeTreeAreaByIds(String ids);

    /**
     * Xóa 信息
     * 
     * @param idTree  ID
     * @return 结果
     */
    public int deleteAttributeTreeAreaById(Long idTree);
}
