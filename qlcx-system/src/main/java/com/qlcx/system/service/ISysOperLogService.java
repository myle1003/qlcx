package com.qlcx.system.service;

import java.util.List;

import com.qlcx.system.domain.SysOperLog;

/**
 * ActionNhật ký 服务层
 * 
 * @author qlcx
 */
public interface ISysOperLogService
{
    /**
     * Thêm vàoActionNhật ký
     * 
     * @param operLog ActionNhật ký对象
     */
    public void insertOperlog(SysOperLog operLog);

    /**
     * 查询系统ActionNhật ký集合
     * 
     * @param operLog ActionNhật ký对象
     * @return ActionNhật ký集合
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 批量Xóa系统ActionNhật ký
     * 
     * @param ids 需要Xóa的数据
     * @return 结果
     */
    public int deleteOperLogByIds(String ids);

    /**
     * 查询ActionNhật ký详细
     * 
     * @param operId ActionID
     * @return ActionNhật ký对象
     */
    public SysOperLog selectOperLogById(Long operId);

    /**
     * 清空ActionNhật ký
     */
    public void cleanOperLog();
}
