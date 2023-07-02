package com.qlcx.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Lớp cấu hình toàn cầu
 */
@Component
@ConfigurationProperties(prefix = "qlcx")
public class Global
{
    /** Tên dự án */
    private static String name;

    /** phiên bản */
    private static String version;

    /** Năm bản quyền */
    private static String copyrightYear;

    /** Chuyển đổi demo */
    private static boolean demoEnabled;

    /** Đường dẫn tải lên */
    private static String profile;

    /** Nhận chuyển đổi địa chỉ */
    private static boolean addressEnabled;

    public static String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        Global.name = name;
    }

    public static String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        Global.version = version;
    }

    public static String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        Global.copyrightYear = copyrightYear;
    }

    public static boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        Global.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        Global.profile = profile;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        Global.addressEnabled = addressEnabled;
    }

    /**
     * Nhận đường dẫn tải lên hình đại diện
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * Nhận đường dẫn tải xuống
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * Nhận đường dẫn tải lên
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }

    /**
     * Nhận đường dẫn uploadBuilding
     */
    public static String getUploadTree()
    {
        return getProfile() + "/upload/tree";
    }
    
    public static String getUploadMessage()
    {
        return getProfile() + "/upload/message";
    }

    public static String getBuildingCategory()
    {
        return getProfile() + "/upload/buildingCategory";
    }
}
