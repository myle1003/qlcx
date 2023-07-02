package com.qlcx.web.controller.test;

import com.qlcx.common.constant.UserConstants;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.framework.shiro.service.SysPasswordService;
import com.qlcx.framework.util.ShiroUtils;
import com.qlcx.system.domain.SysUser;
import com.qlcx.system.service.ISysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    /**
     * 
     * @param user
     * @return user
     */
    @PostMapping("/test-add")
    @ResponseBody
    public AjaxResult addUser (@Validated SysUser user){
        
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
        {
            return error("Add user " + user.getLoginName() + " failed, login account already exists");
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("Add user " + user.getLoginName() + " failed, mobile phone number already exists");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("Add user " + user.getLoginName() + " failed, mailbox account already exists");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getUserName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(user.getLoginName());
        userService.insertUser(user);
        return AjaxResult.success("Add new user successfully", userService.selectUserById(user.getUserId()));
    }


    /**
     * 
     * @param userId
     * @param user
     * @return user
     */
    @PutMapping("/test-update/")
    @ResponseBody
    public AjaxResult updateUser( @Validated SysUser user){

        if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("Modify user " + user.getLoginName() + " failed, mobile phone number already exists");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("Modify user " + user.getLoginName() + " failed, email account already exists");
        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        userService.updateUser(user);
        return AjaxResult.success("User update successfully", userService.selectUserById(user.getUserId()));
    }

    /**
     * @param userId
     * @return user
     */
    @GetMapping("/test-get-user/{userId}")
    @ResponseBody
    public AjaxResult getUser(@PathVariable("userId") Long userId){
        return AjaxResult.success("Get user successfully", userService.selectUserById(userId));
    }

    /**
     * Get all users
     * @return list
     */
    @GetMapping("/test-get-all-user")
    @ResponseBody
    public AjaxResult getAllUsers(SysUser user){
        return AjaxResult.success("Get all users successfully", userService.selectUserList(user));
    }

    /**
     * Delete user
     * @param userId
     */
    @DeleteMapping("/test-delete/")
    @ResponseBody
    public AjaxResult deleteUser(Long userId){
        userService.deleteUserById(userId);
        return AjaxResult.error("User delete successfully");
    }
}