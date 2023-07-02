package com.qlcx.common.core.page;

import com.qlcx.common.constant.Constants;
import com.qlcx.common.utils.ServletUtils;

/**
* Xử lý dữ liệu biểu mẫu
*/
public class TableSupport
{
    /**
    * Gói phân trang đối tượng
    */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
