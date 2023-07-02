package com.qlcx.quartz.service;

import java.util.List;

import com.qlcx.quartz.domain.SysJobLog;

/**
 * Nhiệm vụ theo thời gian调度Nhật ký信息信息 服务层
 * 
 * @author qlcx
 */
public interface ISysJobLogService
{
    /**
     * 获取quartz调度器Nhật ký的计划任务
     * 
     * @param jobLog 调度Nhật ký信息
     * @return 调度任务Nhật ký集合
     */
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog);

    /**
     * 通过调度任务Nhật kýID查询调度信息
     * 
     * @param jobLogId 调度任务Nhật kýID
     * @return 调度任务Nhật ký对象信息
     */
    public SysJobLog selectJobLogById(Long jobLogId);

    /**
     * Thêm vào任务Nhật ký
     * 
     * @param jobLog 调度Nhật ký信息
     */
    public void addJobLog(SysJobLog jobLog);

    /**
     * 批量Xóa调度Nhật ký信息
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    public int deleteJobLogByIds(String ids);

    /**
     * Xóa任务Nhật ký
     * 
     * @param jobId 调度Nhật kýID
     * @return 结果
     */
    public int deleteJobLogById(Long jobId);
    
    /**
     * 清空任务Nhật ký
     */
    public void cleanJobLog();
}
