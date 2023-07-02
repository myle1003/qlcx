package com.qlcx.common.core.domain;

import java.util.HashMap;

import com.qlcx.common.utils.StringUtils;

/**
 * Nhắc nhở thao tác
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** mã trạng thái */
    public static final String CODE_TAG = "code";

    /** Quay lại nội dung */
    public static final String MSG_TAG = "msg";

    /** Đối tượng dữ liệu */
    public static final String DATA_TAG = "data";

    /**
     * Loại trạng thái
     */
    public enum Type
    {
        /** Sự thành công */
        SUCCESS(0),
        /** báo trước */
        WARN(301),
        /** lỗi */
        ERROR(500);
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

    /**
     * Khởi tạo một đối tượng AjaxResult mới được tạo để nó đại diện cho một thông báo trống.
     */
    public AjaxResult()
    {
    }

    /**
     * Khởi tạo đối tượng AjaxResult mới được tạo
     *
     * @param loại trạng thái
     * @param nội dung trả về tin nhắn
     */
    public AjaxResult(Type type, String msg)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * Khởi tạo đối tượng AjaxResult mới được tạo
     *
     * @param loại trạng thái
     * @param nội dung trả về tin nhắn
     * @param dữ liệu dữ liệu đối tượng
     */
    public AjaxResult(Type type, String msg, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * Gửi lại thông báo thành công
     *
     * @return gửi tin thành công
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("Hoạt động thành công");
    }

    /**
     * Trả lại dữ liệu thành công
     *
     * @return gửi tin thành công
     */
    public static AjaxResult success(Object data)
    {
        return AjaxResult.success("Hoạt động thành công", data);
    }

    /**
     * Gửi lại thông báo thành công
     *
     * @param nội dung trả về tin nhắn
     * @return gửi tin thành công
     */
    public static AjaxResult success(String msg)
    {
        return AjaxResult.success(msg, null);
    }

    /**
     *Gửi lại thông báo thành công
     *
     * @param nội dung trả về tin nhắn
     * @param dữ liệu dữ liệu đối tượng
     * @return gửi tin thành công
     */
    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(Type.SUCCESS, msg, data);
    }

    /**
     * Quay lại thông báo cảnh báo
     *
     * @param nội dung trả về tin nhắn
     * @return tin nhắn cảnh báo
     */
    public static AjaxResult warn(String msg)
    {
        return AjaxResult.warn(msg, null);
    }

    /**
     * Quay lại thông báo cảnh báo
     *
     * @param msg trả lại nội dung
     * @param Đối tượng dữ liệu dữ liệu 
     * @param Thông báo cảnh báo 
     */
    public static AjaxResult warn(String msg, Object data)
    {
        return new AjaxResult(Type.WARN, msg, data);
    }

    /**
     * Trả lại thông báo lỗi
     *
     * @return
     */
    public static AjaxResult error()
    {
        return AjaxResult.error("Hoạt động thất bại");
    }

    /**
     * Trả lại thông báo lỗi
     *
     * @param msg trả lại nội dung
     * @return Thông báo cảnh báo 
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }

    /**
     * Trả lại thông báo lỗi
     *
     * @param msg trả lại nội dung
     * @param Đối tượng dữ liệu dữ liệu 
     * @return Thông báo cảnh báo 
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(Type.ERROR, msg, data);
    }
}