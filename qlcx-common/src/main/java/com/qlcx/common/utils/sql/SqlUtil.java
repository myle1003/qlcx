package com.qlcx.common.utils.sql;

import com.qlcx.common.utils.StringUtils;

/**
 * công cụ hoạt động sql
*/
public class SqlUtil
{
    /**
    *Chỉ hỗ trợ chữ cái, số, dấu gạch dưới, dấu cách, dấu phẩy (hỗ trợ sắp xếp nhiều trường)
    */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    /**
    * Kiểm tra các ký tự để ngăn chặn việc bỏ qua tiêm
    */
    public static String escapeOrderBySql(String value)
    {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
    * Xác minh rằng đơn đặt hàng theo cú pháp đáp ứng các thông số kỹ thuật
    */
    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }
}
