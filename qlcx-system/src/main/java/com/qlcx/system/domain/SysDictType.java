package com.qlcx.system.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.annotation.Excel.ColumnType;
import com.qlcx.common.core.domain.BaseEntity;

/**
 * Dictionary type table sys_dict_type
 */
public class SysDictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Dictionary primary key */
    @Excel(name = "Dictionary Primary Key", cellType = ColumnType.NUMERIC)
    private Long dictId;

    /** Dictionary name */
    @Excel(name = "Dictionary name")
    private String dictName;

    /** Dictionary type */
    @Excel(name = "Dictionary Type")
    private String dictType;

    /** Status (0 normal 1 disabled) */
    @Excel(name = "Status", readConverterExp = "0=normal, 1=disabled")
    private String status;

    public Long getDictId()
    {
        return dictId;
    }

    public void setDictId(Long dictId)
    {
        this.dictId = dictId;
    }

    @NotBlank(message = "Dictionary name cannot be empty")
    @Size(min = 0, max = 100, message = "Dictionary type name length cannot exceed 100 characters")
    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }

    @NotBlank(message = "Dictionary type cannot be empty")
    @Size(min = 0, max = 100, message = "Dictionary type cannot exceed 100 characters")
    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dictId", getDictId())
            .append("dictName", getDictName())
            .append("dictType", getDictType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
