package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

public class Cities extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "Mã quận huyện")
    private String name;

    @Excel(name = "Slug")
    private String slug;

    @Excel(name = "Mã tỉnh")
    private Long provinceId;

    @Excel(name = "Mã đất nước")
    private Long countryId;

    @Excel(name = "Del Flag")
    private Integer delete;
    
    private int qttApartment;

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
    public void setProvinceId(Long provinceId) 
    {
        this.provinceId = provinceId;
    }

    public Long getProvinceId() 
    {
        return provinceId;
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

    public int getQttApartment() {
		return qttApartment;
	}

	public void setQttApartment(int qttApartment) {
		this.qttApartment = qttApartment;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("slug", getSlug())
            .append("provinceId", getProvinceId())
            .append("countryId", getCountryId())
            .append("delete", getDelete())
            .toString();
    }
}
