package com.qlcx.quartz.util;

import org.quartz.JobExecutionContext;

import com.qlcx.quartz.domain.SysJob;

/**
 * Nhiệm vụ theo thời gian处理（允许并发执行）
 * 
 * @author qlcx
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
