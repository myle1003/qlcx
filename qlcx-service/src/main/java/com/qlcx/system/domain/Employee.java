package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

/**
 *  对象 employee
 * 
 * @author qlcx
 * @date 2021-02-09
 */
public class Employee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String description;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long idPosition;
    
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String namePosition;
    
    private String email; 
    
    private String longitude;
    
    private String latitude;
    
    private String image;

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
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setIdPosition(Long idPosition) 
    {
        this.idPosition = idPosition;
    }

    public Long getIdPosition() 
    {
        return idPosition;
    }

    public String getNamePosition() {
		return namePosition;
	}

	public void setNamePosition(String namePosition) {
		this.namePosition = namePosition;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .append("idPosition", getIdPosition())
            .toString();
    }
}
