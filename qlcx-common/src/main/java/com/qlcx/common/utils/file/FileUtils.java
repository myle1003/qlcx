package com.qlcx.common.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;

/**
 * File processing tools
 */
public class FileUtils
{
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    /**
     * Đầu ra mảng byte của tệp được chỉ định
     *
     * @param đường dẫn tệp filePath
     * Luồng đầu ra @param os
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b))> 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * Delete Files
     *
     * @param tập tin filePath
     * @return
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        File file = new File(filePath);
        // The path is a file and if it is not empty, delete it
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * Xác minh tên tệp
     *
     * @param Tên tệp  filename
     * @return đúng bình thường sai bất hợp pháp
     */
    public static boolean isValidFilename(String filename)
    {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * Tên tệp tải xuống được mã hóa lại
     *
     * @param yêu cầu đối tượng yêu cầu
     * @param Tên tệp  tên tệp
     * @return Tên tệp được mã hóa 
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE trình duyệt
            filename = URLEncoder.encode(filename, "utf-8'");
            filename = filename.replace("+", "");
        }
        else if (agent.contains("Firefox"))
        {
            // Trình duyệt Firefox
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // trình duyệt google
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // những trình duyệt khác
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
