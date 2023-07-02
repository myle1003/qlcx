package com.qlcx.common.utils.html;

import com.qlcx.common.utils.StringUtils;

/**
 * Lớp công cụ thoát và đảo ngược
 * 
 * @author qlcx
 */
public class EscapeUtil
{
    public static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";

    private static final char[][] TEXT = new char[64][];

    static
    {
        for (int i = 0; i < 64; i++)
        {
            TEXT[i] = new char[] { (char) i };
        }

        // các ký tự HTML đặc biệt
        TEXT['\''] = "&#039;".toCharArray(); // Apostrophe
        TEXT['"'] = "&#34;".toCharArray(); // Apostrophe
        TEXT['&'] = "&#38;".toCharArray(); // &Symbol
        TEXT['<'] = "&#60;".toCharArray(); // Less than sign
        TEXT['>'] = "&#62;".toCharArray(); // Greater than
    }

    /**
    * Các ký tự HTML trong văn bản thoát là các ký tự an toàn
    *
    * @param text Văn bản thoát
    * Văn bản thoát @return
    */
    public static String escape(String text)
    {
        return encode(text);
    }

    /**
     * Khôi phục các ký tự đặc biệt HTML đã thoát
     *
     * @param content Nội dung HTML với các ký tự thoát
     * Chuỗi chuyển đổi @return
     */
    public static String unescape(String content)
    {
        return decode(content);
    }

    /**
     * Xóa tất cả các thẻ HTML, nhưng không xóa nội dung của các thẻ
     *
     * Văn bản nội dung @param
     * @ quay lại xóa văn bản sau nhãn
     */
    public static String clean(String content)
    {
        return new HTMLFilter().filter(content);
    }

    /**
     * Mã hóa thoát
     *
     * @param nhắn tin văn bản được mã hóa
     * Ký tự được mã hóa @return
     */
    private static String encode(String text)
    {
        int len;
        if ((text == null) || ((len = text.length()) == 0))
        {
            return StringUtils.EMPTY;
        }
        StringBuilder buffer = new StringBuilder(len + (len >> 2));
        char c;
        for (int i = 0; i < len; i++)
        {
            c = text.charAt(i);
            if (c < 64)
            {
                buffer.append(TEXT[c]);
            }
            else
            {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    /**
      * Giải mã thoát
      *
      * @param content Escape content
      * Chuỗi được giải mã @return
      */
    public static String decode(String content)
    {
        if (StringUtils.isEmpty(content))
        {
            return content;
        }

        StringBuilder tmp = new StringBuilder(content.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < content.length())
        {
            pos = content.indexOf("%", lastPos);
            if (pos == lastPos)
            {
                if (content.charAt(pos + 1) == 'u')
                {
                    ch = (char) Integer.parseInt(content.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                }
                else
                {
                    ch = (char) Integer.parseInt(content.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            }
            else
            {
                if (pos == -1)
                {
                    tmp.append(content.substring(lastPos));
                    lastPos = content.length();
                }
                else
                {
                    tmp.append(content.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static void main(String[] args)
    {
        String html = "<script>alert(1);</script>";
        // String html = "<scr<script>ipt>alert(\"XSS\")</scr<script>ipt>";
        // String html = "<123";
        System.out.println(EscapeUtil.clean(html));
        System.out.println(EscapeUtil.escape(html));
        System.out.println(EscapeUtil.unescape(html));
    }
}
