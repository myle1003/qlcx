package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

/**
 * Customer account对象 customer_account
 * 
 * @author qlcx
 * @date 2020-09-18
 */
public class CustomerAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** Email */
    @Excel(name = "Email")
    private String email;

    /** Password */
    @Excel(name = "Password")
    private String password;

    /** Salt */
    @Excel(name = "Salt")
    private String salt;

    /** Ho Va Ten */
    @Excel(name = "Ho Va Ten")
    private String fullName;

    /** Status（0 Normal 1 Disabled） */
    @Excel(name = "Status", readConverterExp = "0=,N=ormal,1=,D=isabled")
    private String status;

    /** Delete Flag (0 nomal 2 deleted) */
    private String delFlag;

    /** Login IP */
    @Excel(name = "Login IP")
    private String loginIp;

    /** Login Date */
    @Excel(name = "Login Date")
    private Date loginDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setSalt(String salt) 
    {
        this.salt = salt;
    }

    public String getSalt() 
    {
        return salt;
    }
    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public String getFullName() 
    {
        return fullName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setLoginIp(String loginIp) 
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp() 
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate) 
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate() 
    {
        return loginDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("email", getEmail())
            .append("password", getPassword())
            .append("salt", getSalt())
            .append("fullName", getFullName())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
