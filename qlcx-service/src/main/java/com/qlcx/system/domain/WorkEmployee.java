package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

public class WorkEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idEmployee;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idWork;

    public void setIdEmployee(Long idEmployee) 
    {
        this.idEmployee = idEmployee;
    }

    public Long getIdEmployee() 
    {
        return idEmployee;
    }
    public void setIdWork(Long idWork) 
    {
        this.idWork = idWork;
    }

    public Long getIdWork() 
    {
        return idWork;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("idEmployee", getIdEmployee())
            .append("idWork", getIdWork())
            .toString();
    }
}
