package com.qlcx.system.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.annotation.Excel.ColumnType;
import com.qlcx.common.core.domain.BaseEntity;

/**
 * Role table sys_role
 */
public class SysRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Character ID */
    @Excel(name = "Character Number", cellType = ColumnType.NUMERIC)
    private Long roleId;

    /** Role Name */
    @Excel(name = "role name")
    private String roleName;

    /** Role Permissions */
    @Excel(name = "role permissions")
    private String roleKey;

    /** Character order */
    @Excel(name = "Role Sort", cellType = ColumnType.NUMERIC)
    private String roleSort;

    /** Data range (1: all data permissions; 2: custom data permissions; 3: data permissions of this department; 4: data permissions of this department and below) */
    @Excel(name = "Data Range", readConverterExp = "1=All data permissions, 2=Custom data permissions, 3=Data permissions of this department, 4=Data permissions of this department and below")
    private String dataScope;

    /** Character status (0 normal 1 disabled) */
    @Excel(name = "Character Status", readConverterExp = "0=normal, 1=disabled")
    private String status;

    /** Delete flag (0 means existing, 2 means delete) */
    private String delFlag;

    /** Whether the user has this role ID does not exist by default */
    private boolean flag = false;

    /** Menu group */
    private Long[] menuIds;

    /** Department group (data authority) */
    private Long[] deptIds;

    public SysRole()
    {

    }

    public SysRole(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.roleId);
    }

    public static boolean isAdmin(Long roleId)
    {
        return roleId != null && 1L == roleId;
    }

    public String getDataScope()
    {
        return dataScope;
    }

    public void setDataScope(String dataScope)
    {
        this.dataScope = dataScope;
    }

    @NotBlank(message = "The role name cannot be empty")
     @Size(min = 0, max = 30, message = "The name of the role cannot exceed 30 characters")
     public String getRoleName()
     {
         return roleName;
     }

     public void setRoleName(String roleName)
     {
         this.roleName = roleName;
     }

     @NotBlank(message = "Permission characters cannot be empty")
     @Size(min = 0, max = 100, message = "The length of permission characters cannot exceed 100 characters")
     public String getRoleKey()
     {
         return roleKey;
     }

     public void setRoleKey(String roleKey)
     {
         this.roleKey = roleKey;
     }

     @NotBlank(message = "The display order cannot be empty")
     public String getRoleSort()
     {
         return roleSort;
     }
    public void setRoleSort(String roleSort)
    {
        this.roleSort = roleSort;
    }

    public String getStatus()
    {
        return status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
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

    public Long[] getMenuIds()
    {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds)
    {
        this.menuIds = menuIds;
    }

    public Long[] getDeptIds()
    {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds)
    {
        this.deptIds = deptIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .append("roleKey", getRoleKey())
            .append("roleSort", getRoleSort())
            .append("dataScope", getDataScope())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
