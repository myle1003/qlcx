package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.system.domain.SysLogininfor;
import com.qlcx.system.mapper.SysLogininforMapper;
import com.qlcx.system.service.ISysLogininforService;

/**
 * 系统访问Nhật ký情况信息 服务层处理
 * 
 * @author qlcx
 */
@Service
public class SysLogininforServiceImpl implements ISysLogininforService
{

    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * Thêm vào系统登录Nhật ký
     * 
     * @param logininfor 访问Nhật ký对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor)
    {
        logininforMapper.insertLogininfor(logininfor);
    }

    /**
     * 查询系统登录Nhật ký集合
     * 
     * @param logininfor 访问Nhật ký对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor)
    {
        return logininforMapper.selectLogininforList(logininfor);
    }

    /**
     * 批量Xóa系统登录Nhật ký
     * 
     * @param ids 需要Xóa的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids)
    {
        return logininforMapper.deleteLogininforByIds(Convert.toStrArray(ids));
    }

    /**
     * 清空系统登录Nhật ký
     */
    @Override
    public void cleanLogininfor()
    {
        logininforMapper.cleanLogininfor();
    }
}
