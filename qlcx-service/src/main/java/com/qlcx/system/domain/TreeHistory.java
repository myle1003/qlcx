package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;


public class TreeHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long type;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idCategory;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String description;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idTree;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setIdCategory(Long idCategory) 
    {
        this.idCategory = idCategory;
    }

    public Long getIdCategory() 
    {
        return idCategory;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setIdTree(Long idTree) 
    {
        this.idTree = idTree;
    }

    public Long getIdTree() 
    {
        return idTree;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("createTime", getCreateTime())
            .append("type", getType())
            .append("idCategory", getIdCategory())
            .append("description", getDescription())
            .append("idTree", getIdTree())
            .toString();
    }
}
