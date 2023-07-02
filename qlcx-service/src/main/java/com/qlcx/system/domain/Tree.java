package com.qlcx.system.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

public class Tree extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "Tên cây")
    private String name;

    @Excel(name = "Mã cây")
    private String treeCode;

    @Excel(name = "Kích thước")
    private Long size;

    @Excel(name = "Kinh độ")
    private String longitude;

    @Excel(name = "Vĩ độ")
    private String latitude;

    @Excel(name = "Địa chỉ")
    private String address;
    
    @Excel(name = "Tỉnh")
    private Long provinces;
    
    @Excel(name = "Quận / Huyện")
    private Long cities;
    
    @Excel(name = "Phường / Xã")
    private Long wards;
    
    

    @Excel(name = "Danh mục")
    private Long idCategory;
    
    @Excel(name = "Danh mục")
    private String nameCategory;
    
    @Excel(name = "TT Tưới nước")
    private Long waterTheTree;

    @Excel(name = "TT Tỉa cây")
    private Long prune;

    @Excel(name = "TT bán phân")
    private Long fertilize;

    @Excel(name = "TT Chống cây")
    private Long againstTree;

    @Excel(name = "TT Làm sạch quanh")
    private Long cleanTheStump;
    
    @Excel(name = "TT Làm sạch quanh")
    private Long statusCleanTheStump;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long statusAgainstTree;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long statusFertilize;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long statusPrune;
    
    private Date takeCareDay;
    
    private Date createNew;
    
    private int qttUpdate;
    
    private String image;
    
    private List<TreeAttributes> listAttribute;
    
    
    
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long statusWaterTheTree;
    
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long status;
    
    private Long statusUpdate;
    
    private Long type;

    public Date getCreateNew() {
		return createNew;
	}

	public void setCreateNew(Date createNew) {
		this.createNew = createNew;
	}

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
    public void setTreeCode(String treeCode) 
    {
        this.treeCode = treeCode;
    }

    public String getTreeCode() 
    {
        return treeCode;
    }
    public void setSize(Long size) 
    {
        this.size = size;
    }

    public Long getSize() 
    {
        return size;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getProvinces() {
		return provinces;
	}

	public void setProvinces(Long provinces) {
		this.provinces = provinces;
	}

	public Long getCities() {
		return cities;
	}

	public void setCities(Long cities) {
		this.cities = cities;
	}

	public Long getWards() {
		return wards;
	}

	public void setWards(Long wards) {
		this.wards = wards;
	}

	public void setIdCategory(Long idCategory) 
    {
        this.idCategory = idCategory;
    }

    public Long getIdCategory() 
    {
        return idCategory;
    }

    public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getWaterTheTree() {
		return waterTheTree;
	}

	public void setWaterTheTree(Long waterTheTree) {
		this.waterTheTree = waterTheTree;
	}

	public Long getPrune() {
		return prune;
	}

	public void setPrune(Long prune) {
		this.prune = prune;
	}

	public Long getFertilize() {
		return fertilize;
	}

	public void setFertilize(Long fertilize) {
		this.fertilize = fertilize;
	}

	public Long getAgainstTree() {
		return againstTree;
	}

	public void setAgainstTree(Long againstTree) {
		this.againstTree = againstTree;
	}

	public Long getCleanTheStump() {
		return cleanTheStump;
	}

	public void setCleanTheStump(Long cleanTheStump) {
		this.cleanTheStump = cleanTheStump;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getStatusCleanTheStump() {
		return statusCleanTheStump;
	}

	public void setStatusCleanTheStump(Long statusCleanTheStump) {
		this.statusCleanTheStump = statusCleanTheStump;
	}

	public Long getStatusAgainstTree() {
		return statusAgainstTree;
	}

	public void setStatusAgainstTree(Long statusAgainstTree) {
		this.statusAgainstTree = statusAgainstTree;
	}

	public Long getStatusFertilize() {
		return statusFertilize;
	}

	public void setStatusFertilize(Long statusFertilize) {
		this.statusFertilize = statusFertilize;
	}

	public Long getStatusPrune() {
		return statusPrune;
	}

	public Date getTakeCareDay() {
		return takeCareDay;
	}

	public void setTakeCareDay(Date takeCareDay) {
		this.takeCareDay = takeCareDay;
	}

	public void setStatusPrune(Long statusPrune) {
		this.statusPrune = statusPrune;
	}

	public int getQttUpdate() {
		return qttUpdate;
	}

	public void setQttUpdate(int qttUpdate) {
		this.qttUpdate = qttUpdate;
	}

	public Long getStatusWaterTheTree() {
		return statusWaterTheTree;
	}

	public void setStatusWaterTheTree(Long statusWaterTheTree) {
		this.statusWaterTheTree = statusWaterTheTree;
	}

	
	

	public List<TreeAttributes> getListAttribute() {
		return listAttribute;
	}

	public void setListAttribute(List<TreeAttributes> listAttribute) {
		this.listAttribute = listAttribute;
	}

	public Long getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(Long statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("treeCode", getTreeCode())
            .append("size", getSize())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("idCategory", getIdCategory())
            .toString();
    }
}
