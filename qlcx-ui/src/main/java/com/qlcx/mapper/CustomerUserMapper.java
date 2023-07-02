package com.qlcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.qlcx.domain.CustomerUser;


@Mapper
public interface CustomerUserMapper 
{
    
    public CustomerUser selectCustomerUserById(Long id);
    
    public CustomerUser selectCustomerUserByUserNamePass(@Param("nameLogin") String nameLogin,@Param("password") String password);


    public List<CustomerUser> selectCustomerUserList(CustomerUser customerUser);
    public int insertCustomerUser(CustomerUser customerUser);
    public int updateCustomerUser(CustomerUser customerUser);


    public int deleteCustomerUserById(Long id);

    public int deleteCustomerUserByIds(String[] ids);
}
