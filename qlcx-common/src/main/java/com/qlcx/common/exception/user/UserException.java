package com.qlcx.common.exception.user;

import com.qlcx.common.exception.base.BaseException;

/**
 *Thông tin người dùng ngoại lệ
 * 
 * @author qlcx
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
