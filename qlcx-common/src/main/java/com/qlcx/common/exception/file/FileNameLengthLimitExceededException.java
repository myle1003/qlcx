package com.qlcx.common.exception.file;

/**
 * Lớp ngoại lệ hạn chế vượt quá độ dài tên tệp
 * 
 * @author qlcx
 */
public class FileNameLengthLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
