package com.qlcx.common.core.page;

import java.io.Serializable;
import java.util.List;

/**
 * Đối tượng dữ liệu phân trang bảng
*/
public class TableDataInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** toàn bộ */
    private long total;

    /** Liệt kê dữ liệu */
    private List<?> rows;

    /** Mã trạng thái tin nhắn */
    private int code;

    /** Nội dung tin nhắn */
    private String msg;

    /**
    * Đối tượng dữ liệu bảng
    */
    public TableDataInfo()
    {
    }

    /**
    * Phân trang
    *
    * @param Dữ liệu danh sách  
    * @param tổng số bản ghi
    */
    public TableDataInfo(List<?> list, int total)
    {
        this.rows = list;
        this.total = total;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public List<?> getRows()
    {
        return rows;
    }

    public void setRows(List<?> rows)
    {
        this.rows = rows;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}