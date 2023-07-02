package com.qlcx.system.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.annotation.Excel.ColumnType;
import com.qlcx.common.core.domain.BaseEntity;

/**
 *Parameter configuration table sys_config
 * 
 * @author qlcx
 */
public class SysConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Parameter primary key */
    @Excel(name = "Parameter primary key", cellType = ColumnType.NUMERIC)
    private Long configId;

    /** Parameter name */
    @Excel(name = "Parameter name")
    private String configName;

    /** Parameter key name */
    @Excel(name = "Parameter key name")
    private String configKey;

    /** Parameter key */
    @Excel(name = "Parameter key")
    private String configValue;

    /** Built-in system (Y is N or not) */
    @Excel(name = "Built-in system", readConverterExp = "Y=Yes, N=No")
    private String configType;

    public Long getConfigId()
    {
        return configId;
    }

    public void setConfigId(Long configId)
    {
        this.configId = configId;
    }

    @NotBlank(message = "Parameter name cannot be empty")
    @Size(min = 0, max = 100, message = "Parameter name cannot exceed 100 characters")
    public String getConfigName()
    {
        return configName;
    }

    public void setConfigName(String configName)
    {
        this.configName = configName;
    }

    @NotBlank(message = "Parameter key name length cannot be empty")
    @Size(min = 0, max = 100, message = "Parameter key name cannot exceed 100 characters")
    public String getConfigKey()
    {
        return configKey;
    }

    public void setConfigKey(String configKey)
    {
        this.configKey = configKey;
    }

    @NotBlank(message = "Parameter key cannot be empty")
    @Size(min = 0, max = 500, message = "Parameter key length cannot exceed 500 characters")
    public String getConfigValue()
    {
        return configValue;
    }
    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
    }

    public String getConfigType()
    {
        return configType;
    }

    public void setConfigType(String configType)
    {
        this.configType = configType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("configId", getConfigId())
            .append("configName", getConfigName())
            .append("configKey", getConfigKey())
            .append("configValue", getConfigValue())
            .append("configType", getConfigType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
