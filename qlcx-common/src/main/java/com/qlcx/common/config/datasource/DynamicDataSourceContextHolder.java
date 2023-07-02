package com.qlcx.common.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Xử lý chuyển đổi nguồn dữ liệu
 */
public class DynamicDataSourceContextHolder
{
    public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    /**
     * Sử dụng ThreadLocal để duy trì các biến, ThreadLocal cung cấp các bản sao độc lập của các biến cho mỗi luồng sử dụng biến,
     * Vì vậy mỗi luồng có thể thay đổi bản sao của chính nó một cách độc lập mà không ảnh hưởng đến bản sao tương ứng với các luồng khác.
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * Đặt biến nguồn dữ liệu
     */
    public static void setDataSourceType(String dsType)
    {
        log.info("Switch to {}data source", dsType);
        CONTEXT_HOLDER.set(dsType);
    }

    /**
     * Nhận các biến nguồn dữ liệu
     */
    public static String getDataSourceType()
    {
        return CONTEXT_HOLDER.get();
    }

    /**
     * Xóa các biến nguồn dữ liệu
     */
    public static void clearDataSourceType()
    {
        CONTEXT_HOLDER.remove();
    }
}