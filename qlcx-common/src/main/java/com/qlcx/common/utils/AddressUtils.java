package com.qlcx.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.qlcx.common.config.Global;
import com.qlcx.common.constant.Constants;
import com.qlcx.common.utils.http.HttpUtils;

/**
 * Nhận lớp địa chỉ
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    // Truy vấn địa chỉ IP
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    //địa chỉ không xác định
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip)
    {
        String address = UNKNOWN;
        // Intranet không truy vấn
        if (IpUtils.internalIp(ip))
        {
            return "Intranet IP";
        }
        if (Global.isAddressEnabled())
        {
            try
            {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
                if (StringUtils.isEmpty(rspStr))
                {
                    log.error("Get abnormal geographic location {}", ip);
                    return UNKNOWN;
                }
                JSONObject obj = JSONObject.parseObject(rspStr);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            }
            catch (Exception e)
            {
                log.error("Abnormal location acquisition {}", e);
            }
        }
        return address;
    }
}
