package com.qlcx.system.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.annotation.Excel.ColumnType;
import com.qlcx.common.core.domain.BaseEntity;

/**
 * Post table sys_post
 */
public class SysPost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Job number */
    @Excel(name = "post number", cellType = ColumnType.NUMERIC)
    private Long postId;

    /** Job code */
    @Excel(name = "Job Code")
    private String postCode;

    /** position Name */
    @Excel(name = "post name")
    private String postName;

    /** Job ranking */
    @Excel(name = "Job Sorting", cellType = ColumnType.NUMERIC)
    private String postSort;

    /** Status (0 normal 1 disabled) */
    @Excel(name = "Status", readConverterExp = "0=normal, 1=disabled")
    private String status;

    /** Whether the user has this post ID does not exist by default */
    private boolean flag = false;

    public Long getPostId()
    {
        return postId;
    }

    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    @NotBlank(message = "Post code cannot be empty")
    @Size(min = 0, max = 64, message = "The length of the post code cannot exceed 64 characters")
    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    @NotBlank(message = "Job name cannot be empty")
    @Size(min = 0, max = 50, message = "The length of the post name cannot exceed 50 characters")
    public String getPostName()
    {
        return postName;
    }

    public void setPostName(String postName)
    {
        this.postName = postName;
    }

    @NotBlank(message = "The display order cannot be empty")
    public String getPostSort()
    {
        return postSort;
    }

    public void setPostSort(String postSort)
    {
        this.postSort = postSort;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("postId", getPostId())
            .append("postCode", getPostCode())
            .append("postName", getPostName())
            .append("postSort", getPostSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
