package com.qlcx.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.qlcx.common.utils.spring.SpringUtils;

/**
 * Nhận tệp tài nguyên i18n
*/
public class MessageUtils
{
    /**
    * Nhận thông báo dựa trên các khóa và tham số thông báo và ủy quyền cho thông báo mùa xuân
    *
    * @param Khóa tin nhắn mã 
    * @param Thông số  args
    * @return Nhận giá trị bản dịch được quốc tế hóa
    */
    public static String message(String code, Object... args)
    {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
