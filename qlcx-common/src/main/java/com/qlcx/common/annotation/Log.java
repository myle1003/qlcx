package com.qlcx.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qlcx.common.enums.BusinessType;
import com.qlcx.common.enums.OperatorType;

/**
  * Ghi chú về bản ghi nhật ký hoạt động tùy chỉnh
  *
  * @author qlcx
  */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * Mô-đun
    */
    public String title() default "";

    /**
     * Features
    */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * Danh mục nhà điều hành
    */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * Có lưu các thông số được yêu cầu hay không
    */
    public boolean isSaveRequestData() default true;
}