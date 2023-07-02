package com.qlcx.common.exception.user;

/**
 * Tài khoản người dùng đã bị xóa
 * 
 * @author qlcx
 */
public class UserDeleteException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserDeleteException()
    {
        super("user.password.delete", null);
    }
}
