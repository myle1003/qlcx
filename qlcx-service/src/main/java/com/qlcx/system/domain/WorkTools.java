package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

public class WorkTools extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idWork;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idTools;

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
    public void setIdTools(Long idTools) 
    {
        this.idTools = idTools;
    }

    public Long getIdTools() 
    {
        return idTools;
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
            .append("idTools", getIdTools())
            .append("description", getDescription())
            .toString();
    }
}
