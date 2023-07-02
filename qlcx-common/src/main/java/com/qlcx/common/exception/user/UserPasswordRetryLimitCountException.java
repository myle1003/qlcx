package com.qlcx.common.exception.user;

/**
 * Lớp ngoại lệ đếm lỗi người dùng
 * 
 * @author qlcx
 */
public class UserPasswordRetryLimitCountException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitCountException(int retryLimitCount)
    {
        super("user.password.retry.limit.count", new Object[] { retryLimitCount });
    }
}
