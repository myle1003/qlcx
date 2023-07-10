package com.qlcx.quartz.service;

import java.util.List;
import org.quartz.SchedulerException;

import com.qlcx.common.exception.job.TaskException;
import com.qlcx.quartz.domain.SysJob;


public interface ISysJobService
{

    public List<SysJob> selectJobList(SysJob job);


    public SysJob selectJobById(Long jobId);


    public int pauseJob(SysJob job) throws SchedulerException;

    public int resumeJob(SysJob job) throws SchedulerException;

    public int deleteJob(SysJob job) throws SchedulerException;

    public void deleteJobByIds(String ids) throws SchedulerException;

    public int changeStatus(SysJob job) throws SchedulerException;

    public void run(SysJob job) throws SchedulerException;

    public int insertJob(SysJob job) throws SchedulerException, TaskException;

    public int updateJob(SysJob job) throws SchedulerException, TaskException;

    public boolean checkCronExpressionIsValid(String cronExpression);
}