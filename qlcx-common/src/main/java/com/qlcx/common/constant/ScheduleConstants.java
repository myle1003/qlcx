package com.qlcx.common.constant;

/**
 * Hằng số chung cho lập lịch tác vụ
 */
public interface ScheduleConstants
{
    public static final String TASK_CLASS_NAME = "TASK_CLASS_NAME";

    /** Khóa mục tiêu thực thi */
    public static final String TASK_PROPERTIES = "TASK_PROPERTIES";

    /** mặc định */
    public static final String MISFIRE_DEFAULT = "0";

    /** thực hiện thực hiện ngay lập tức */
    public static final String MISFIRE_IGNORE_MISFIRES = "1";

    /** Kích hoạt thực thi */
    public static final String MISFIRE_FIRE_AND_PROCEED = "2";

    /** thực thi ngay lập tức mà không cần kích hoạt */
    public static final String MISFIRE_DO_NOTHING = "3";

    public enum Status
    {
        /**
         * Bình thường
         */
        NORMAL("0"),
        /**
         * hết giờ
         */
        PAUSE("1");

        private String value;

        private Status(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }
}