package com.qlcx.common.enums;

/**
  *Loại hình hoạt động kinh doanh
  */
public enum BusinessType
{
     /**
      * khác
      */
     OTHER,

     /**
      * mới
      */
     INSERT,

     /**
      * Sửa đổi
      */
     UPDATE,

     /**
      * xóa
      */
     DELETE,

     /**
      * Ủy quyền
      */
     GRANT,

     /**
      * Xuất file Excel
      */
     EXPORT,

     /**
      * Nhập khẩu
      */
     IMPORT,

     /**
      * Rút lui
      */
     FORCE,

     /**
      * Tạo mã
      */
     GENCODE,
    
     /**
      * Rỗng
      */
     CLEAN,
}