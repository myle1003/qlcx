package com.qlcx.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qlcx.common.enums.DataSourceType;

/**
 * Ghi chú chuyển đổi nhiều nguồn dữ liệu tùy chỉnh
*
* Ưu tiên: phương thức đầu tiên, sau đó đến lớp, nếu phương thức bao gồm kiểu nguồn dữ liệu trên lớp thì phương thức đó sẽ chiếm ưu thế, nếu không thì lớp sẽ chiếm ưu thế
*/
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    /**
    * Chuyển đổi tên nguồn dữ liệu
    */
    public DataSourceType value() default DataSourceType.MASTER;
}
