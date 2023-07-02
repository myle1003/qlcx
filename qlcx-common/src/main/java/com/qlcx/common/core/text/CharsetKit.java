package com.qlcx.common.core.text;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.qlcx.common.utils.StringUtils;

/**
 * Công cụ thiết lập ký tự
 */
public class CharsetKit
{
    /** ISO-8859-1 */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /** UTF-8 */
    public static final String UTF_8 = "UTF-8";
    /** GBK */
    public static final String GBK = "GBK";

    /** ISO-8859-1 */
    public static final Charset CHARSET_ISO_8859_1 = Charset.forName(ISO_8859_1);
    /** UTF-8 */
    public static final Charset CHARSET_UTF_8 = Charset.forName(UTF_8);
    /** GBK */
    public static final Charset CHARSET_GBK = Charset.forName(GBK);

    /**
     * Chuyển đổi sang đối tượng Charset
     *
     * @param  Bộ ký tự ký tự, nếu trống, nó trả về bộ ký tự mặc định
     * @return Bộ chữ
     */
    public static Charset charset(String charset)
    {
        return StringUtils.isEmpty(charset)? Charset.defaultCharset(): Charset.forName(charset);
    }

    /**
     * Chuyển đổi mã hóa bộ ký tự của chuỗi
     *
     * @param chuỗi nguồn
     * @param srcCharset bộ ký tự nguồn, mặc định ISO-8859-1
     * @param Bộ ký tự đích đích destCharset, UTF-8 mặc định
     * @return bộ ký tự chuyển đổi
     */
    public static String convert(String source, String srcCharset, String destCharset)
    {
        return convert(source, Charset.forName(srcCharset), Charset.forName(destCharset));
    }

    /**
     * Chuyển đổi mã hóa bộ ký tự của chuỗi
     *
     * @param chuỗi nguồn
     * @param srcCharset bộ ký tự nguồn, mặc định ISO-8859-1
     * @param Bộ ký tự đích đích destCharset, UTF-8 mặc định
     * @return bộ ký tự chuyển đổi
     */
    public static String convert(String source, Charset srcCharset, Charset destCharset)
    {
        if (null == srcCharset)
        {
            srcCharset = StandardCharsets.ISO_8859_1;
        }

        if (null == destCharset)
        {
            srcCharset = StandardCharsets.UTF_8;
        }

        if (StringUtils.isEmpty(source) || srcCharset.equals(destCharset))
        {
            return source;
        }
        return new String(source.getBytes(srcCharset), destCharset);
    }

    /**
     * @return mã hóa bộ ký tự hệ thống
     */
    public static String systemCharset()
    {
        return Charset.defaultCharset().name();
    }
}