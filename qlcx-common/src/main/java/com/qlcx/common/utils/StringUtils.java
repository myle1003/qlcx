package com.qlcx.common.utils;

import java.util.Collection;
import java.util.Map;

import com.qlcx.common.core.text.StrFormatter;

/**
 * Công cụ chuỗi
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils
{
    /** chuỗi trống */
    private static final String NULLSTR = "";

    /** gạch chân */
    private static final char SEPARATOR ='_';

    /**
     * Tham số Get không rỗng
     *
     * @param value defaultValue Giá trị được đánh giá
     * @return value return value
     */
    public static <T> T nvl(T value, T defaultValue)
    {
        return value != null? value: defaultValue;
    }

    /**
     * * Xác định xem Bộ sưu tập có trống không, bao gồm Danh sách, Tập hợp, Hàng đợi
     *
     * @param coll Bộ sưu tập được đánh giá
     * @return true: rỗng false: không rỗng
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * Xác định xem Bộ sưu tập có trống không, bao gồm Danh sách, Tập hợp, Hàng đợi
     *
     * @param coll Bộ sưu tập được đánh giá
     * @return true: không rỗng false: trống
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    /**
     * * Xác định xem một mảng đối tượng có trống không
     *
     * @param đối tượng mảng các đối tượng được đánh giá
     ** @return true: blank false: không trống
     */
    public static boolean isEmpty(Object[] objects)
    {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * Xác định xem một mảng đối tượng có trống không
     *
     * @param đối tượng mảng các đối tượng được đánh giá
     * @return true: không trống false: trống rỗng
     */
    public static boolean isNotEmpty(Object[] objects)
    {
        return !isEmpty(objects);
    }

    /**
     * * Xác định xem Bản đồ có trống không
     *
     * @param Bản đồ được đánh giá
     * @return true: blank false: không trống
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * Xác định xem Bản đồ có trống không
     *
     * @param Bản đồ  Bản đồ được đánh giá
     * @return true: không trống false: trống rỗng
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }

    /**
     * *Xác định xem một chuỗi có phải là một chuỗi rỗng hay không
     *
     * @param Chuỗi  str
     * @return true: blank false: không trống
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * Xác định xem một chuỗi có phải là một chuỗi không rỗng
     *
     * @param Chuỗi  str
     * @return true: chuỗi không rỗng false: chuỗi rỗng
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * * Xác định xem một đối tượng có trống không
     *
     * @param Đối tượng  object
     * @return true: blank false: không trống
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * * Xác định xem một đối tượng có trống không
     *
     * @param Đối tượng  object
     * @return true: không trống false: trống rỗng
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * * Xác định xem một đối tượng có phải là một kiểu mảng hay không (một mảng các kiểu cơ bản của Java)
     *
     * @param Đối tượng 
     * @return true: là một mảng sai: không phải là một mảng
     */
    public static boolean isArray(Object object)
    {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * Đi tới không gian
     */
    public static String trim(String str)
    {
        return (str == null? "": str.trim());
    }

    /**
     *Chuỗi chặn
     *
     * @param Chuỗi str 
     * @param bắt đầu
     * @return kết quả
     */
    public static String substring(final String str, int start)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = str.length() + start;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (start > str.length())
        {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
      * Chuỗi chặn
      *
      * @param Chuỗi str 
      * @param bắt đầu
      * @param end
      * @return kết quả
      */
    public static String substring(final String str, int start, int end)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (end < 0)
        {
            end = str.length() + end;
        }
        if (start < 0)
        {
            start = str.length() + start;
        }

        if (end > str.length())
        {
            end = str.length();
        }

        if (start > end)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (end < 0)
        {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * Văn bản được định dạng, {} có nghĩa là trình giữ chỗ <br>
     * Phương pháp này chỉ thay thế trình giữ chỗ {} bằng các tham số theo thứ tự <br>
     * Nếu bạn muốn xuất {}, hãy sử dụng \\ Escape {, nếu bạn muốn xuất \ trước {}, hãy sử dụng ký tự thoát kép \\\\ <br>
     * Ví dụ: <br>
     * Thường được sử dụng: format ("this is {} for {}", "a", "b") -> this is a for b <br>
     * Escape {}: format ("this is \\ {} for {}", "a", "b") -> this is \ {} for a <br>
     * Escape \: format ("this is \\\\ {} cho {}", "a", "b") -> đây là \ a cho b <br>
     *
     * Mẫu văn bản mẫu @param, phần được thay thế được biểu thị bằng {}
     * Giá trị tham số @param params
     * Văn bản được định dạng @return
     */
    public static String format(String template, Object... params)
    {
        if (isEmpty(params) || isEmpty(template))
        {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * Gạch chân tên bướu
     */
    public static String toUnderScoreCase(String str)
    {
        if (str == null)
        {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // Cho dù ký tự đứng đầu là chữ hoa
        boolean preCharIsUpperCase = true;
        // Cho dù ký tự hiện tại là chữ hoa
        boolean curreCharIsUpperCase = true;
        // Ký tự tiếp theo có được viết hoa hay không
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (i > 0)
            {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            }
            else
            {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1))
            {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * Có bao gồm chuỗi không
     *
     * @param Chuỗi xác minh  str
     * @param Nhóm chuỗi  strs
     * @return chứa trả về true
     */
    public static boolean inStringIgnoreCase(String str, String... strs)
    {
        if (str != null && strs != null)
        {
            for (String s: strs)
            {
                if (str.equalsIgnoreCase(trim(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Chuyển đổi chuỗi ký tự có tên viết hoa gạch dưới sang viết hoa lạc đà. Nếu chuỗi được đặt tên bởi dấu gạch dưới trước khi chuyển đổi trống, một chuỗi trống được trả về. Ví dụ: HELLO_WORLD-> HelloWorld
     *
     * @param Chuỗi ký tự được đặt tên bằng dấu gạch dưới trước khi chuyển đổi
     * @return đã chuyển đổi chuỗi có tên kiểu bướu
     */
    public static String convertToCamelCase(String name)
    {
        StringBuilder result = new StringBuilder();
        //Kiểm tra nhanh
        if (name == null || name.isEmpty())
        {
            // không cần chuyển đổi
            return "";
        }
        else if (!name.contains("_"))
        {
            // Không có gạch dưới, chỉ viết hoa chữ cái đầu tiên
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // Không có gạch dưới, chỉ viết hoa chữ cái đầu tiên
        String[] camels = name.split("_");
        for (String camel: camels)
        {
            //Bỏ qua dấu gạch dưới đầu và cuối hoặc gấp đôi dấu gạch dưới trong chuỗi gốc
            if (camel.isEmpty())
            {
                continue;
            }
            // Viết hoa chữ cái đầu tiên
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * Danh pháp kiểu gù Ví dụ: user_name-> userName
     */
    public static String toCamelCase(String s)
    {
        if (s == null)
        {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (c == SEPARATOR)
            {
                upperCase = true;
            }
            else if (upperCase)
            {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            }
            else
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj)
    {
        return (T) obj;
    }
}