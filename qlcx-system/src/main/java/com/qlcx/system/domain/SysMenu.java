package com.qlcx.system.domain;

import java.util.List;
import java.util.ArrayList;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.core.domain.BaseEntity;

/**
 * Menu permission table sys_menu
 */
public class SysMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Menu ID */
    private Long menuId;

    /** Menu name */
    private String menuName;

    /** Name of parent menu */
    private String parentName;

    /** Parent menu ID */
    private Long parentId;

    /** display order */
    private String orderNum;

    /** Menu URL */
    private String url;

    /** Opening method: menuItem tab tab menuBlank new window */
    private String target;

    /** Type: 0 directory, 1 menu, 2 buttons */
    private String menuType;

    /** Menu status: 0 display, 1 hidden */
    private String visible;

    /** permission string */
    private String perms;

    /** Menu icon */
    private String icon;

    /** Submenu */
    private List<SysMenu> children = new ArrayList<SysMenu>();

    public Long getMenuId()
    {
        return menuId;
    }

    public void setMenuId(Long menuId)
    {
        this.menuId = menuId;
    }

    @NotBlank(message = "Menu name cannot be empty")
    @Size(min = 0, max = 50, message = "The menu name cannot exceed 50 characters")
    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

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

    @NotBlank(message = "The display order cannot be empty")
     public String getOrderNum()
     {
         return orderNum;
     }

     public void setOrderNum(String orderNum)
     {
         this.orderNum = orderNum;
     }

     @Size(min = 0, max = 200, message = "The requested address cannot exceed 200 characters")
     public String getUrl()
     {
         return url;
     }

     public void setUrl(String url)
     {
         this.url = url;
     }

     public String getTarget()
     {
         return target;
     }

     public void setTarget(String target)
     {
         this.target = target;
     }

     @NotBlank(message = "Menu type cannot be empty")
     public String getMenuType()
     {
         return menuType;
     }
    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public String getVisible()
    {
        return visible;
    }

    public void setVisible(String visible)
    {
        this.visible = visible;
    }

    @Size(min = 0, max = 100, message = "The length of the permission ID cannot exceed 100 characters")
    public String getPerms()
    {
        return perms;
    }

    public void setPerms(String perms)
    {
        this.perms = perms;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public List<SysMenu> getChildren()
    {
        return children;
    }

    public void setChildren(List<SysMenu> children)
    {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("menuId", getMenuId())
            .append("menuName", getMenuName())
            .append("parentId", getParentId())
            .append("orderNum", getOrderNum())
            .append("url", getUrl())
            .append("target", getTarget())
            .append("menuType", getMenuType())
            .append("visible", getVisible())
            .append("perms", getPerms())
            .append("icon", getIcon())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
