package com.qlcx.common.core.text;

import com.qlcx.common.utils.StringUtils;

/**
 * Định dạng chuỗi
 * 
 * @author qlcx
 */
public class StrFormatter
{
    public static final String EMPTY_JSON = "{}";
    public static final char C_BACKSLASH = '\\';
    public static final char C_DELIM_START = '{';
    public static final char C_DELIM_END = '}';

    /**
      * Định dạng chuỗi <br>
      * Phương pháp này chỉ thay thế trình giữ chỗ {} bằng các tham số theo thứ tự <br>
      * Nếu bạn muốn xuất ra {}, hãy sử dụng \\ Escape {, nếu bạn muốn xuất \ trước {}, hãy sử dụng ký tự thoát kép \\\\ <br>
      * Ví dụ: <br>
      * Thường được sử dụng: format ("this is {} for {}", "a", "b") -> this is a for b <br>
      * Escape {}: format ("this is \\ {} for {}", "a", "b") -> this is \ {} for a <br>
      * Escape \: format ("đây là \\\\ {} cho {}", "a", "b") -> đây là \ a cho b <br>
      *
      * @param mẫu chuỗi strPattern
      * @param danh sách tham số argArray
      * @return kết quả
      */
    public static String format(final String strPattern, final Object... argArray)
    {
        if (StringUtils.isEmpty(strPattern) || StringUtils.isEmpty(argArray))
        {
            return strPattern;
        }
        final int strPatternLength = strPattern.length();

        // Khởi tạo độ dài xác định để có hiệu suất tốt hơn
        StringBuilder sbuf = new StringBuilder(strPatternLength + 50);

        int handledPosition = 0;
        int delimIndex;//Vị trí của trình giữ chỗ
        for (int argIndex = 0; argIndex < argArray.length; argIndex++)
        {
            delimIndex = strPattern.indexOf(EMPTY_JSON, handledPosition);
            if (delimIndex == -1)
            {
                if (handledPosition == 0)
                {
                    return strPattern;
                }
                else
                { // Phần còn lại của mẫu chuỗi không còn chứa trình giữ chỗ và kết quả được trả về sau khi Thêm phần còn lại
                    sbuf.append(strPattern, handledPosition, strPatternLength);
                    return sbuf.toString();
                }
            }
            else
            {
                if (delimIndex> 0 && strPattern.charAt(delimIndex-1) == C_BACKSLASH)
                {
                    if (delimIndex> 1 && strPattern.charAt(delimIndex-2) == C_BACKSLASH)
                    {
                        // Cũng có một ký tự thoát trước ký tự thoát và trình giữ chỗ vẫn hợp lệ
                        sbuf.append(strPattern, handledPosition, delimIndex-1);
                        sbuf.append(Convert.utf8Str(argArray[argIndex]));
                        handledPosition = delimIndex + 2;
                    }
                    else
                    {
                        // trình giữ chỗ bị thoát
                        argIndex--;
                        sbuf.append(strPattern, handledPosition, delimIndex - 1);
                        sbuf.append(C_DELIM_START);
                        handledPosition = delimIndex + 1;
                    }
                }
                else
                {
                    // trình giữ chỗ bình thường
                    sbuf.append(strPattern, handledPosition, delimIndex);
                    sbuf.append(Convert.utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + 2;
                }
            }
        }
        // Thêm tất cả các ký tự sau phần giữ chỗ cuối cùng
        sbuf.append(strPattern, handledPosition, strPattern.length());

        return sbuf.toString();
    }
}
