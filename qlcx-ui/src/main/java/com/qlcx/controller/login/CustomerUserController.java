package com.qlcx.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.domain.CustomerUser;
import com.qlcx.service.CustomerUserService;


@Controller
@RequestMapping("/login")
public class CustomerUserController 
{
    private String prefix = "user";

    @Autowired
    private CustomerUserService customerUserService;

    @GetMapping("/sign-in")
    public String userSignIn(ModelMap mmap)
    {
    	mmap.put("user", "sign-in");
        return prefix + "/login";
    }
    
    @GetMapping("/sign-up")
    public String userSSignUp(ModelMap mmap)
    {
    	mmap.put("user", "sign-up");
        return prefix + "/login";
    }
    
    @PostMapping("/sign-in")
    @ResponseBody
    public AjaxResult login(@RequestBody CustomerUser customerUser)
    {
    	CustomerUser cus =  customerUserService.selectCustomerUserByUserNamePass(customerUser.getNameLogin(),customerUser.getPassword());
    	if(cus!=null)
    	{
    		return AjaxResult.success("Đăng nhập thành công",cus);
    	}else {
    		return AjaxResult.error("Đăng nhập thất bại");
		}
    }
    
    
    
    @PostMapping("/sign-up")
    @ResponseBody
    public AjaxResult addSave(@RequestBody CustomerUser customerUser)
    {   
    	 return AjaxResult.success(customerUserService.insertCustomerUser(customerUser));
    }

    @GetMapping("/update")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CustomerUser customerUser = customerUserService.selectCustomerUserById(id);
        mmap.put("customerUser", customerUser);
        return prefix + "/edit";
    }

    @PostMapping("/update")
    @ResponseBody
    public AjaxResult editSave(CustomerUser customerUser)
    {
        return  AjaxResult.success(customerUserService.updateCustomerUser(customerUser));
    }

}
