package com.qlcx.common.core.controller;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.core.domain.AjaxResult.Type;
import com.qlcx.common.core.page.PageDomain;
import com.qlcx.common.core.page.TableDataInfo;
import com.qlcx.common.core.page.TableSupport;
import com.qlcx.common.utils.DateUtils;
import com.qlcx.common.utils.ServletUtils;
import com.qlcx.common.utils.StringUtils;
import com.qlcx.common.utils.sql.SqlUtil;

/**
 * Xử lý dữ liệu chung của lớp web
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * Chuỗi định dạng ngày được chuyển từ nền trước được tự động chuyển đổi thành loại Ngày
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date type conversion
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * Đặt dữ liệu phân trang yêu cầu
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * Đặt dữ liệu sắp xếp yêu cầu
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * Nhận yêu cầu
     */
    public HttpServletRequest getRequest()
    {
        return ServletUtils.getRequest();
    }

    /**
     * Nhận được phản ứng
     */
    public HttpServletResponse getResponse()
    {
        return ServletUtils.getResponse();
    }

    /**
     * Nhận phiên
     */
    public HttpSession getSession()
    {
        return getRequest().getSession();
    }

    /**
     * Phân trang dữ liệu theo yêu cầu
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * Kết quả trả về phản hồi
     *
     * @param hàng ảnh hưởng đến số lượng hàng
     * @return kết quả hoạt động
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * Kết quả trả về phản hồi
     *
     * @param kết quả
     * @return kết quả hoạt động
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result? success(): error();
    }

    /**
     * Trả lại thành công
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * Trả lại thông báo lỗi
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * Gửi lại thông báo thành công
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * Trả lại thông báo lỗi
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * Trả lại thông báo mã lỗi
     */
    public AjaxResult error(Type type, String message)
    {
        return new AjaxResult(type, message);
    }

    /**
     * Bước nhảy trang
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    public boolean getAbsoluteFile(String uploadDir) throws IOException
    {
        File desc = new File(uploadDir);

        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return true;
    }
}