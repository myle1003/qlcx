package com.qlcx.common.core.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Chọn lớp thực thể cấu trúc cây
*/
public class CxSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
    * Tên trường giá trị dữ liệu
    */
    private String v;

    /**
    * Tên trường tiêu đề dữ liệu
    */
    private String n;

    /**
    * Tên trường dữ liệu tập hợp con
    */
    private List<CxSelect> s;

    public CxSelect()
    {
    }

    public CxSelect(String v, String n)
    {
        this.v = v;
        this.n = n;
    }

    public List<CxSelect> getS()
    {
        return s;
    }

    public void setN(String n)
    {
        this.n = n;
    }

    public String getN()
    {
        return n;
    }

    public void setS(List<CxSelect> s)
    {
        this.s = s;
    }

    public String getV()
    {
        return v;
    }

    public void setV(String v)
    {
        this.v = v;
    }
}
