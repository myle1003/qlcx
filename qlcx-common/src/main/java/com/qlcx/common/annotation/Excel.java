package com.qlcx.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Xuất tùy chỉnh chú thích dữ liệu Excel
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel
{
    /**
     * Tên được xuất sang Excel.
     */
    public String name() default "";

    /**
     * Định dạng ngày, chẳng hạn như: yyyy-MM-dd
     */
    public String dateFormat() default "";

    /**
     * Đọc nội dung thành biểu thức (ví dụ: 0 = Nam, 1 = Nữ, 2 = Không xác định)
     */
    public String readConverterExp() default "";

    /**
     * Loại xuất (0 chữ số 1 chuỗi)
     */
    public ColumnType cellType() default ColumnType.STRING;

    /**
     * Đơn vị chiều cao của mỗi cột trong Excel khi xuất là ký tự
     */
    public double height() default 14;

    /**
     * Chiều rộng của mỗi cột trong excel là ký tự khi xuất
     */
    public double width() default 16;

    /**
     * Chiều rộng của mỗi cột trong excel là ký tự khi xuất
     */
    public String suffix() default "";

    /**
     * Khi giá trị trống, giá trị mặc định của trường
     */
    public String defaultValue() default "";

    /**
     * Nhắc nhở
     */
    public String prompt() default "";

    /**
     * Cài đặt chỉ có thể chọn nội dung cột không thể nhập.
     */
    public String[] combo() default {};

    /**
     *Xuất dữ liệu có đáp ứng được nhu cầu hay không: Đôi khi chúng ta cần xuất một mẫu, mẫu này cần cho tiêu đề nhưng nội dung cần được người dùng điền thủ công.
     */
    public boolean isExport() default true;

    /**
     * Tên thuộc tính trong một lớp khác, hỗ trợ chuyển đổi nhiều cấp, được phân tách bằng dấu thập phân
     */
    public String targetAttr() default "";

    /**
     * Loại trường (0: xuất nhập; 1: chỉ xuất; 2: chỉ nhập)
     */
    Type type() default Type.ALL;

    public enum Type
    {
        ALL(0), EXPORT(1), IMPORT(2);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    public enum ColumnType
    {
        NUMERIC(0), STRING(1);
        private final int value;

        ColumnType(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }
}