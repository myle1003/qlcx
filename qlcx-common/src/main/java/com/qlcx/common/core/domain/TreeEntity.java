package com.qlcx.common.core.domain;

/**
 * Lớp cơ sở cây
*/
public class TreeEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Tên của menu mẹ */
    private String parentName;

    /** ID menu chính */
    private Long parentId;

    /** thứ tự hiển thị */
    private Integer orderNum;

    /** Danh sách tổ tiên */
    private String ancestors;

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }
}