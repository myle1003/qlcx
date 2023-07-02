package com.qlcx.framework.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.qlcx.common.constant.Constants;
import com.qlcx.common.constant.ShiroConstants;
import com.qlcx.common.constant.UserConstants;
import com.qlcx.common.utils.MessageUtils;
import com.qlcx.common.utils.ServletUtils;
import com.qlcx.framework.manager.AsyncManager;
import com.qlcx.framework.manager.factory.AsyncFactory;
import com.qlcx.framework.util.ShiroUtils;
import com.qlcx.system.domain.SysUser;
import com.qlcx.system.service.ISysUserService;

/**
 * Registration verification method
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    /**
     * Register
     */
    public String register(SysUser user)
    {
        String msg = "", username = user.getLoginName(), password = user.getPassword();

        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            msg = "Wrong verification code";
        }
        else if (StringUtils.isEmpty(username))
        {
            msg = "Username cannot be empty";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "User password cannot be empty";
        }
        else if (password.length() <UserConstants.PASSWORD_MIN_LENGTH
                || password.length()> UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "Password must be between 5 and 20 characters";
        }
        else if (username.length() <UserConstants.USERNAME_MIN_LENGTH
                || username.length()> UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "Account length must be between 2 and 20 characters";
        }
        else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(username)))
        {
            msg = "Save user'" + username + "'failed, the registered account already exists";
        }
        else
        {
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
            boolean regFlag = userService.registerUser(user);
            if (!regFlag)
            {
                msg = "Registration failed, please contact the system administrator";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }
}
