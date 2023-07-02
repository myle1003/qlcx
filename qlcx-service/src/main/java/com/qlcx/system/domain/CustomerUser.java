package com.qlcx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

/**
 *  对象 customer_user
 * 
 * @author qlcx
 * @date 2021-04-09
 */
public class CustomerUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String nameLogin;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String password;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNameLogin(String nameLogin) 
    {
        this.nameLogin = nameLogin;
    }

    public String getNameLogin() 
    {
        return nameLogin;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nameLogin", getNameLogin())
            .append("password", getPassword())
            .append("userName", getUserName())
            .toString();
    }
}
