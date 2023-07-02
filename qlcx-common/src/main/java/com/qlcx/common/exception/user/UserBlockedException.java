package com.qlcx.common.exception.user;

/**
 * Lớp ngoại lệ khóa người dùng
 * 
 * @author qlcx
 */
public class UserBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserBlockedException()
    {
        super("user.blocked", null);
    }
}
