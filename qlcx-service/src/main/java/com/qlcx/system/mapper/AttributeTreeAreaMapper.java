package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.AttributeTreeArea;

public interface AttributeTreeAreaMapper 
{

    public AttributeTreeArea selectAttributeTreeAreaById(Long idTree);

    public List<AttributeTreeArea> selectAttributeTreeAreaList(AttributeTreeArea attributeTreeArea);

    public int insertAttributeTreeArea(AttributeTreeArea attributeTreeArea);

    public int updateAttributeTreeArea(AttributeTreeArea attributeTreeArea);

    public int deleteAttributeTreeAreaByIdTree(Long id);
    
    public int deleteAttributeTreeAreaByIdCategory(Long id);

    public int deleteAttributeTreeAreaByIds(String[] idTrees);
}
