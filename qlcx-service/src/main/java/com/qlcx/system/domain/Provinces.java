package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 provinces
 * 
 * @author apm
 * @date 2020-10-23
 */
public class Provinces extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "Mã tỉnh")
    private String name;

    @Excel(name = "Slug")
    private String slug;

    @Excel(name = "Mã quốc gia")
    private Long countryId;

    /** del lag */
    @Excel(name = "del lag")
    private Integer delete;

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
    public void setSlug(String slug) 
    {
        this.slug = slug;
    }

    public String getSlug() 
    {
        return slug;
    }
    public void setCountryId(Long countryId) 
    {
        this.countryId = countryId;
    }

    public Long getCountryId() 
    {
        return countryId;
    }
    public void setDelete(Integer delete) 
    {
        this.delete = delete;
    }

    public Integer getDelete() 
    {
        return delete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("slug", getSlug())
            .append("countryId", getCountryId())
            .append("delete", getDelete())
            .toString();
    }
}
