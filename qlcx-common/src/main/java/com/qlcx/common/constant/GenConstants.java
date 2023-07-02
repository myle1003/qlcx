package com.qlcx.common.constant;

/**
 * Hằng số chung tạo mã
 */
public class GenConstants
{
    /** Bảng đơn (Thêm, xóa và sửa đổi) */
    public static final String TPL_CRUD = "crud";

    /** Bảng cây (Thêm, xóa và sửa đổi) */
    public static final String TPL_TREE = "tree";

    /** Trường mã hóa cây */
    public static final String TREE_CODE = "treeCode";

    /** Trường mã hóa gốc cây */
    public static final String TREE_PARENT_CODE = "treeParentCode";

    /** Trường tên cây */
    public static final String TREE_NAME = "treeName";

    /** Loại chuỗi cơ sở dữ liệu */
    public static final String[] COLUMNTYPE_STR = {"char", "varchar", "narchar", "varchar2", "tinytext", "text",
            "mediumtext", "longtext" };

    /** Loại thời gian cơ sở dữ liệu */
    public static final String[] COLUMNTYPE_TIME = {"datetime'", "time", "date", "timestamp" };

    /** Loại số cơ sở dữ liệu */
    public static final String[] COLUMNTYPE_NUMBER = {"tinyint", "smallint", "mediumint", "int", "number", "integer",
            "bigint", "float", "float", "double", "decimal" };

    /** Trang không cần Sửa trường */
    public static final String[] COLUMNNAME_NOT_EDIT = {"id", "create_by'", "create_time'", "del_flag'" };

    /** Liệt kê các trường không cần hiển thị trên trang */
    public static final String[] COLUMNNAME_NOT_LIST = {"id", "create_by", "create_time", "del_flag", "update_by",
            "update_time" };

    /** Trang không cần trường truy vấn */
    public static final String[] COLUMNNAME_NOT_QUERY = {"id", "create_by", "create_time", "del_flag", "update_by",
            "update_time", "remark" };

    /** Trường lớp cơ sở thực thể */
    public static final String[] BASE_ENTITY = {"createBy", "createTime", "updateBy", "updateTime", "remark" };

    /** Trường lớp cơ sở cây */
    public static final String[] TREE_ENTITY = {"parentName", "parentId", "orderNum", "ancestors" };

    /** hộp văn bản */
    public static final String HTML_INPUT = "input";

    /** trương Văn bản */
    public static final String HTML_TEXTAREA = "textarea";

    /** thả cái hộp xuống */
    public static final String HTML_SELECT = "select";

    /** Hộp đơn */
    public static final String HTML_RADIO = "radio";

    /** hộp kiểm tra */
    public static final String HTML_CHECKBOX = "checkbox";

    /** Kiểm soát ngày tháng */
    public static final String HTML_DATETIME = "datetime";

    /** String type */
    public static final String TYPE_STRING = "String";

    /** integer */
    public static final String TYPE_INTEGER = "Integer";

    /** Long integer */
    public static final String TYPE_LONG = "Long";

    /** floating point */
    public static final String TYPE_DOUBLE = "Double";

    /** Loại tính toán chính xác cao */
    public static final String TYPE_BIGDECIMAL = "BigDecimal";

    /** Time type */
    public static final String TYPE_DATE = "Date";

    /** Fuzzy query */
    public static final String QUERY_LIKE = "LIKE";

    /** required */
    public static final String REQUIRE = "1";
}