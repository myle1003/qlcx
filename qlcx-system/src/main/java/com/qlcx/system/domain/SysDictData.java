package com.qlcx.system.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.annotation.Excel.ColumnType;
import com.qlcx.common.constant.UserConstants;
import com.qlcx.common.core.domain.BaseEntity;

/**
 * Dictionary data table sys_dict_data
 */
public class SysDictData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Dictionary encoding */
    @Excel(name = "Dictionary encoding", cellType = ColumnType.NUMERIC)
    private Long dictCode;

    /** Dictionary sorting */
    @Excel(name = "Dictionary sorting", cellType = ColumnType.NUMERIC)
    private Long dictSort;

    /** dictionary tags */
    @Excel(name = "Dictionary Label")
    private String dictLabel;

    /** dictionary key */
    @Excel(name = "Dictionary key value")
    private String dictValue;

    /** Dictionary type */
    @Excel(name = "Dictionary Type")
    private String dictType;

    /** Style attributes (other style extensions) */
    @Excel(name = "Dictionary Style")
    private String cssClass;

    /** Table dictionary style */
    private String listClass;

    /** Is it the default (Y is N or not) */
    @Excel(name = "is it default", readConverterExp = "Y=yes, N=no")
    private String isDefault;

    /** Status (0 normal 1 disabled) */
    @Excel(name = "Status", readConverterExp = "0=normal, 1=disabled")
    private String status;

    public Long getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(Long dictCode)
    {
        this.dictCode = dictCode;
    }

    public Long getDictSort()
    {
        return dictSort;
    }

    public void setDictSort(Long dictSort)
    {
        this.dictSort = dictSort;
    }

    @NotBlank(message = "Dictionary label cannot be empty")
    @Size(min = 0, max = 100, message = "Dictionary label length cannot exceed 100 characters")
    public String getDictLabel()
    {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel)
    {
        this.dictLabel = dictLabel;
    }

    @NotBlank(message = "Dictionary key cannot be empty")
    @Size(min = 0, max = 100, message = "Dictionary key length cannot exceed 100 characters")
    public String getDictValue()
    {
        return dictValue;
    }

    public void setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
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

    @Size(min = 0, max = 100, message = "style attribute length cannot exceed 100 characters")
    public String getCssClass()
    {
        return cssClass;
    }

    public void setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
    }

    public String getListClass()
    {
        return listClass;
    }

    public void setListClass(String listClass)
    {
        this.listClass = listClass;
    }

    public boolean getDefault()
    {
        return UserConstants.YES.equals(this.isDefault) ? true : false;
    }

    public String getIsDefault()
    {
        return isDefault;
    }

    public void setIsDefault(String isDefault)
    {
        this.isDefault = isDefault;
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
            .append("dictCode", getDictCode())
            .append("dictSort", getDictSort())
            .append("dictLabel", getDictLabel())
            .append("dictValue", getDictValue())
            .append("dictType", getDictType())
            .append("cssClass", getCssClass())
            .append("listClass", getListClass())
            .append("isDefault", getIsDefault())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
