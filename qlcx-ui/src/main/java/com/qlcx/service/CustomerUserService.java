package com.qlcx.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.domain.CustomerUser;
import com.qlcx.mapper.CustomerUserMapper;
import com.qlcx.utils.MD5Util;

@Service
public class CustomerUserService 
{
    @Autowired
    private CustomerUserMapper customerUserMapper;

    
    
    public CustomerUser selectCustomerUserById(Long id)
    {
        return customerUserMapper.selectCustomerUserById(id);
    }
    
    public CustomerUser selectCustomerUserByUserNamePass(String nameLogin,String passwork)
    {
    	try {
    		System.out.println("name"+ nameLogin +" pass" + passwork);
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    byte[] messageDigest = md.digest(passwork.getBytes());
		    new MD5Util();
		    passwork = MD5Util.convertByteToHex(messageDigest);
		  } catch (NoSuchAlgorithmException e) {
		    throw new RuntimeException(e);
		  }   
    	
        return customerUserMapper.selectCustomerUserByUserNamePass(nameLogin,passwork);
    }


    public List<CustomerUser> selectCustomerUserList(CustomerUser customerUser)
    {
        return customerUserMapper.selectCustomerUserList(customerUser);
    }

    public int insertCustomerUser(CustomerUser customerUser)
    {
    	try {
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    byte[] messageDigest = md.digest(customerUser.getPassword().getBytes());
		    new MD5Util();
		    customerUser.setPassword(MD5Util.convertByteToHex(messageDigest));	   
		  } catch (NoSuchAlgorithmException e) {
		    throw new RuntimeException(e);
		  }  
        return customerUserMapper.insertCustomerUser(customerUser);
    }

    public int updateCustomerUser(CustomerUser customerUser)
    {
        return customerUserMapper.updateCustomerUser(customerUser);
    }

    public int deleteCustomerUserByIds(String ids)
    {
        return customerUserMapper.deleteCustomerUserByIds(Convert.toStrArray(ids));
    }

    public int deleteCustomerUserById(Long id)
    {
        return customerUserMapper.deleteCustomerUserById(id);
    }
}
