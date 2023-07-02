package com.qlcx.common.exception.file;

import com.qlcx.common.exception.base.BaseException;

/**
 * File information exception
 * 
 * @author qlcx
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
