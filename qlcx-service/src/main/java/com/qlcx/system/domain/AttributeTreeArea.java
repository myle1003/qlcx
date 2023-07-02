package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

public class AttributeTreeArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idTree;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idCategory;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idAttribute;

    public void setIdTree(Long idTree) 
    {
        this.idTree = idTree;
    }

    public Long getIdTree() 
    {
        return idTree;
    }
    public void setIdCategory(Long idCategory) 
    {
        this.idCategory = idCategory;
    }

    public Long getIdCategory() 
    {
        return idCategory;
    }
    public void setIdAttribute(Long idAttribute) 
    {
        this.idAttribute = idAttribute;
    }

    public Long getIdAttribute() 
    {
        return idAttribute;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("idTree", getIdTree())
            .append("idCategory", getIdCategory())
            .append("idAttribute", getIdAttribute())
            .toString();
    }
}
