package com.qlcx.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ghi chú lọc quyền dữ liệu
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
    * Bí danh của bảng phòng ban
    */
    public String deptAlias() default "";

    /**
    * Bí danh bảng người dùng
    */
    public String userAlias() default "";
}
