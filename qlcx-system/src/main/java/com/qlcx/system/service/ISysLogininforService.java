package com.qlcx.system.service;

import java.util.List;

import com.qlcx.system.domain.SysLogininfor;

/**
 * 系统访问Nhật ký情况信息 服务层
 * 
 * @author qlcx
 */
public interface ISysLogininforService
{
    /**
     * Thêm vào系统登录Nhật ký
     * 
     * @param logininfor 访问Nhật ký对象
     */
    public void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录Nhật ký集合
     * 
     * @param logininfor 访问Nhật ký对象
     * @return 登录记录集合
     */
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * 批量Xóa系统登录Nhật ký
     * 
     * @param ids 需要Xóa的数据
     * @return
     */
    public int deleteLogininforByIds(String ids);

    /**
     * 清空系统登录Nhật ký
     */
    public void cleanLogininfor();
}
