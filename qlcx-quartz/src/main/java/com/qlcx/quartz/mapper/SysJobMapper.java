package com.qlcx.quartz.mapper;

import java.util.List;

import com.qlcx.quartz.domain.SysJob;

/**
 * 调度任务信息 数据层
 * 
 * @author qlcx
 */
public interface SysJobMapper
{
    /**
     * 查询调度任务Nhật ký集合
     * 
     * @param job 调度信息
     * @return ActionNhật ký集合
     */
    public List<SysJob> selectJobList(SysJob job);

    /**
     * 查询tất cả调度任务
     * 
     * @return 调度任务Danh sách
     */
    public List<SysJob> selectJobAll();

    /**
     * 通过调度ID查询调度任务信息
     * 
     * @param jobId 调度ID
     * @return 角色对象信息
     */
    public SysJob selectJobById(Long jobId);

    /**
     * 通过调度IDXóa调度任务信息
     * 
     * @param jobId 调度ID
     * @return 结果
     */
    public int deleteJobById(Long jobId);

    /**
     * 批量Xóa调度任务信息
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    public int deleteJobByIds(Long[] ids);

    /**
     *sửa đổi调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int updateJob(SysJob job);

    /**
     * Thêm vào调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int insertJob(SysJob job);
}
