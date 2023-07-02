package com.qlcx.common.constant;

/**
 * Hằng số tổng quát Shiro
 */
public interface ShiroConstants
{
    /**
     * Người dùng hiện đã đăng nhập
     */
    public static final String CURRENT_USER = "currentUser";

    /**
     * tên tài khoản
     */
    public static final String CURRENT_USERNAME = "username";

    /**
     * tên tài khoản
     */
    public static final String MESSAGE = "message";

    /**
     * Chìa khóa sai
     */
    public static final String ERROR = "errorMsg";

    /**
     * Định dạng mã hóa
     */
    public static final String ENCODING = "UTF-8";

    /**
     * Phiên trực tuyến hiện tại
     */
    public static final String ONLINE_SESSION = "online_session";

    /**
     * Khóa mã xác minh
     */
    public static final String CURRENT_CAPTCHA = "captcha";

    /**
     * Chuyển đổi mã xác minh
     */
    public static final String CURRENT_ENABLED = "captchaEnabled";

    /**
     * Loại mã xác minh
     */
    public static final String CURRENT_TYPE = "captchaType";

    /**
     * Verification code
     */
    public static final String CURRENT_VALIDATECODE = "validateCode";

    /**
     * Lỗi mã xác minh
     */
    public static final String CAPTCHA_ERROR = "captchaError";

    /**
     * Bộ nhớ cache của hồ sơ đăng nhập
     */
    public static final String LOGINRECORDCACHE = "loginRecordCache";

    /**
     * 	Hệ thống đang hoạt động bộ nhớ cache của người dùng
     */
    public static final String SYS_USERCACHE = "sys-userCache";
}