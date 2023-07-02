package com.qlcx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.core.domain.BaseEntity;


public class CustomerUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    private Long id;
    private String nameLogin;
    private String password;
    private String confirmPassword;
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

    public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
