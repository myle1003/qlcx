package com.qlcx.quartz.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.quartz.domain.SysJobLog;
import com.qlcx.quartz.mapper.SysJobLogMapper;
import com.qlcx.quartz.service.ISysJobLogService;

/**
 * Nhiệm vụ theo thời gian调度Nhật ký信息 服务层
 * 
 * @author qlcx
 */
@Service
public class SysJobLogServiceImpl implements ISysJobLogService
{
    @Autowired
    private SysJobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器Nhật ký的计划任务
     * 
     * @param jobLog 调度Nhật ký信息
     * @return 调度任务Nhật ký集合
     */
    @Override
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog)
    {
        return jobLogMapper.selectJobLogList(jobLog);
    }

    /**
     * 通过调度任务Nhật kýID查询调度信息
     * 
     * @param jobLogId 调度任务Nhật kýID
     * @return 调度任务Nhật ký对象信息
     */
    @Override
    public SysJobLog selectJobLogById(Long jobLogId)
    {
        return jobLogMapper.selectJobLogById(jobLogId);
    }

    /**
     * Thêm vào任务Nhật ký
     * 
     * @param jobLog 调度Nhật ký信息
     */
    @Override
    public void addJobLog(SysJobLog jobLog)
    {
        jobLogMapper.insertJobLog(jobLog);
    }

    /**
     * 批量Xóa调度Nhật ký信息
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    @Override
    public int deleteJobLogByIds(String ids)
    {
        return jobLogMapper.deleteJobLogByIds(Convert.toStrArray(ids));
    }

    /**
     * Xóa任务Nhật ký
     * 
     * @param jobId 调度Nhật kýID
     */
    @Override
    public int deleteJobLogById(Long jobId)
    {
        return jobLogMapper.deleteJobLogById(jobId);
    }

    /**
     * 清空任务Nhật ký
     */
    @Override
    public void cleanJobLog()
    {
        jobLogMapper.cleanJobLog();
    }
}
