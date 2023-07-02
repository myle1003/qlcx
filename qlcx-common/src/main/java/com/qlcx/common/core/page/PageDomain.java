package com.qlcx.common.core.page;

import com.qlcx.common.utils.StringUtils;

/**
* Dữ liệu trang
*/
public class PageDomain
{
    /** Chỉ mục bắt đầu của bản ghi hiện tại */
    private Integer pageNum;
    /** Hiển thị số lượng bản ghi trên mỗi trang */
    private Integer pageSize;
    /** sắp xếp cột */
    private String orderByColumn;
    /** Hướng sắp xếp "desc" hoặc "asc". */
    private String isAsc;

    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        this.isAsc = isAsc;
    }
}
