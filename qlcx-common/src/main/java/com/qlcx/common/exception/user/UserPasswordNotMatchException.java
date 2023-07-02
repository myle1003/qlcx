package com.qlcx.common.exception.user;

/**
 *Mật khẩu của người dùng không chính xác hoặc không đáp ứng các thông số kỹ thuật
 * 
 * @author qlcx
 */
public class UserPasswordNotMatchException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("user.password.not.match", null);
    }
}
