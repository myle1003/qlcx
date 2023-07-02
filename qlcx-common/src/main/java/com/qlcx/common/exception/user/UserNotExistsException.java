package com.qlcx.common.exception.user;

/**
 * Không có lớp ngoại lệ cho người dùng
 * 
 * @author qlcx
 */
public class UserNotExistsException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("user.not.exists", null);
    }
}
