package com.qlcx.common.exception.base;

import com.qlcx.common.utils.MessageUtils;
import com.qlcx.common.utils.StringUtils;

/**
 * Bất thường cơ bản
*/
public class BaseException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
    * Thuộc về mô-đun
    */
    private String module;

    /**
    * mã lỗi
    */
    private String code;

    /**
    * Các thông số tương ứng với mã lỗi
    */
    private Object[] args;

    /**
    * thông tin sai
    */
    private String defaultMessage;

    public BaseException(String module, String code, Object[] args, String defaultMessage)
    {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args)
    {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage)
    {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args)
    {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage)
    {
        this(null, null, null, defaultMessage);
    }


	@Override
    public String getMessage()
    {
        String message = null;
        if (!StringUtils.isEmpty(code))
        {
            message = MessageUtils.message(code, args);
        }
        if (message == null)
        {
            message = defaultMessage;
        }
        return message;
    }

    public String getModule()
    {
        return module;
    }

    public String getCode()
    {
        return code;
    }

    public Object[] getArgs()
    {
        return args;
    }

    public String getDefaultMessage()
    {
        return defaultMessage;
    }
}
