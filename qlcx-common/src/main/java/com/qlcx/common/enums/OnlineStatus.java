package com.qlcx.common.enums;

/**
  * Phiên người dùng
  */
public enum OnlineStatus
{
     /** tâm trạng người dùng */
     on_line("Online"), off_line("Offline");

     private final String info;

     private OnlineStatus(String info)
     {
         this.info = info;
     }

     public String getInfo()
     {
         return info;
     }
}