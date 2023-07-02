package com.qlcx.common.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import com.qlcx.common.utils.ServletUtils;

/**
 * Service related configuration
*
*/
@Component
public class ServerConfig
{
    /**
    * Nhận đường dẫn yêu cầu hoàn chỉnh, bao gồm: tên miền, cổng, đường dẫn truy cập ngữ cảnh
    *
    * @return service address
    */
    public String getUrl()
    {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    public static String getDomain(HttpServletRequest request)
    {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length()-request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
