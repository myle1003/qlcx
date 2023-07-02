package com.qlcx.common.exception.user;

/**
 * Lớp ngoại lệ lỗi mã xác minh
 * 
 * @author qlcx
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
