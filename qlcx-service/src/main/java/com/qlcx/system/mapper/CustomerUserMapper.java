package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.CustomerUser;

public interface CustomerUserMapper 
{
 
    public CustomerUser selectCustomerUserById(Long id);

    public List<CustomerUser> selectCustomerUserList(CustomerUser customerUser);

    public int insertCustomerUser(CustomerUser customerUser);

    public int updateCustomerUser(CustomerUser customerUser);
  
    public int deleteCustomerUserById(Long id);
  
    public int deleteCustomerUserByIds(String[] ids);
}
