package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

public class WorkTree extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idWork;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idTree;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String description;

    public void setIdWork(Long idWork) 
    {
        this.idWork = idWork;
    }

    public Long getIdWork() 
    {
        return idWork;
    }
    public void setIdTree(Long idTree) 
    {
        this.idTree = idTree;
    }

    public Long getIdTree() 
    {
        return idTree;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("idWord", getIdWork())
            .append("idTree", getIdTree())
            .append("description", getDescription())
            .toString();
    }
}
