package com.qlcx.common.constant;

/**
 * Thông tin liên tục của người dùng
 */
public class UserConstants
{
    /**
     * Biểu trưng duy nhất của người dùng hệ thống trong nền tảng
     */
    public static final String SYS_USER = "SYS_USER";

    /** tình trạng bình thường */
    public static final String NORMAL = "0";

    /** Trạng thái bất thường */
    public static final String EXCEPTION = "1";

    /** Trạng thái cấm người dùng */
    public static final String USER_DISABLE = "1";

    /** Trạng thái nhân vật bị cấm */
    public static final String ROLE_DISABLE = "1";

    /** Bộ phận tình trạng bình thường */
    public static final String DEPT_NORMAL = "0";

    /** Tình trạng ngừng hoạt động của bộ phận */
    public static final String DEPT_DISABLE = "1";

    /** Trạng thái từ điển bình thường */
    public static final String DICT_NORMAL = "0";

    /** Nó có phải là mặc định của hệ thống không (Có) */
    public static final String YES = "Y";

    /**
     * Giới hạn độ dài tên người dùng
     */
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;

    /** Tên đăng nhập có phải là mã kết quả trả về duy nhất hay không */
    public final static String USER_NAME_UNIQUE = "0";
    public final static String USER_NAME_NOT_UNIQUE = "1";

    /** Cho dù số điện thoại di động là kết quả duy nhất */
    public final static String USER_PHONE_UNIQUE = "0";
    public final static String USER_PHONE_NOT_UNIQUE = "1";

    /** E-mail có phải là kết quả duy nhất được trả về không */
    public final static String USER_EMAIL_UNIQUE = "0";
    public final static String USER_EMAIL_NOT_UNIQUE = "1";

    /** Tên bộ phận có phải là mã kết quả trả về duy nhất hay không */
    public final static String DEPT_NAME_UNIQUE = "0";
    public final static String DEPT_NAME_NOT_UNIQUE = "1";

    /** Tên vai trò có phải là mã kết quả trả về duy nhất hay không */
    public final static String ROLE_NAME_UNIQUE = "0";
    public final static String ROLE_NAME_NOT_UNIQUE = "1";

    /** Tên công việc có phải là mã kết quả trả về duy nhất hay không */
    public final static String POST_NAME_UNIQUE = "0";
    public final static String POST_NAME_NOT_UNIQUE = "1";

    /** Quyền vai trò có phải là mã kết quả trả về duy nhất hay không */
    public final static String ROLE_KEY_UNIQUE = "0";
    public final static String ROLE_KEY_NOT_UNIQUE = "1";

    /** Mã công việc có phải là mã kết quả trả về duy nhất hay không */
    public final static String POST_CODE_UNIQUE = "0";
    public final static String POST_CODE_NOT_UNIQUE = "1";

    /** Tên menu có phải là mã kết quả trả về duy nhất hay không */
    public final static String MENU_NAME_UNIQUE = "0";
    public final static String MENU_NAME_NOT_UNIQUE = "1";

    /** Loại từ điển có phải là mã kết quả trả về duy nhất hay không */
    public final static String DICT_TYPE_UNIQUE = "0";
    public final static String DICT_TYPE_NOT_UNIQUE = "1";

    /** Tên khóa tham số có phải là mã kết quả trả về duy nhất hay không */
    public final static String CONFIG_KEY_UNIQUE = "0";
    public final static String CONFIG_KEY_NOT_UNIQUE = "1";

    /**
     * Giới hạn độ dài mật khẩu
     */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 20;

    /**
     * kiểu người dùng
     */
    public static final String SYSTEM_USER_TYPE = "00";
    public static final String REGISTER_USER_TYPE = "01";

    /**
     * Giới hạn định dạng số điện thoại di động
     */
    public static final String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8 }$";

    /**
     * Giới hạn định dạng email
     */
    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
}
