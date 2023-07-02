package com.qlcx.common.exception.user;

/**
 * Ngoại lệ khóa vai trò
 * 
 * @author qlcx
 */
public class RoleBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public RoleBlockedException()
    {
        super("role.blocked", null);
    }
}
