package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

public class Wards extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "Tên xã phường")
    private String name;

    @Excel(name = "slug")
    private String slug;

    @Excel(name = "Mã quốc gia")
    private Long countryId;

    @Excel(name = "Mã tỉnh")
    private Long provinceId;

    @Excel(name = "Mã quyận huyện")
    private Long cityId;

    @Excel(name = "Del flag")
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
    public void setProvinceId(Long provinceId) 
    {
        this.provinceId = provinceId;
    }

    public Long getProvinceId() 
    {
        return provinceId;
    }
    public void setCityId(Long cityId) 
    {
        this.cityId = cityId;
    }

    public Long getCityId() 
    {
        return cityId;
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
            .append("provinceId", getProvinceId())
            .append("cityId", getCityId())
            .append("delete", getDelete())
            .toString();
    }
}
