package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.annotation.Excel.ColumnType;
import com.qlcx.common.core.domain.BaseEntity;

/**
 * System access record table sys_logininfor
 */
public class SysLogininfor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "Serial Number", cellType = ColumnType.NUMERIC)
    private Long infoId;

    /** user account */
    @Excel(name = "User Account")
    private String loginName;

    /** Login status 0 success 1 failure */
    @Excel(name = "login status", readConverterExp = "0=success, 1=failure")
    private String status;

    /** Login IP address */
    @Excel(name = "Login Address")
    private String ipaddr;

    /** Login location */
    @Excel(name = "Login Location")
    private String loginLocation;

    /** Browser type */
    @Excel(name = "Browser")
    private String browser;

    /** operating system */
    @Excel(name = "Operating System")
    private String os;

    /** Prompt message */
    @Excel(name = "Prompt Message")
    private String msg;

    /** interview time */
    @Excel(name = "Access Time", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    
    public Long getInfoId()
    {
        return infoId;
    }

    public void setInfoId(Long infoId)
    {
        this.infoId = infoId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("infoId", getInfoId())
            .append("loginName", getLoginName())
            .append("ipaddr", getIpaddr())
            .append("loginLocation", getLoginLocation())
            .append("browser", getBrowser())
            .append("os", getOs())
            .append("status", getStatus())
            .append("msg", getMsg())
            .append("loginTime", getLoginTime())
            .toString();
    }
}