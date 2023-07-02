package com.qlcx.common.core.domain;

import java.io.Serializable;

/**
 * Lớp thực thể cấu trúc cây Ztree
*/
public class Ztree implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** Node ID */
    private Long id;

    /** Node cha ID */
    private Long pId;

    /** Node tên */
    private String name;

    /** Node tiêu dề */
    private String title;

    /** Đã kiểm tra */
    private boolean checked = false;

    /** Có mở rộng không */
    private boolean open = false;

    private boolean nocheck = false;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getpId()
    {
        return pId;
    }

    public void setpId(Long pId)
    {
        this.pId = pId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public boolean isOpen()
    {
        return open;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }

    public boolean isNocheck()
    {
        return nocheck;
    }

    public void setNocheck(boolean nocheck)
    {
        this.nocheck = nocheck;
    }
}
