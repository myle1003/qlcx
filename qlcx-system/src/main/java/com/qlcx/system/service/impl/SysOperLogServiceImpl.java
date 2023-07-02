package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.system.domain.SysOperLog;
import com.qlcx.system.mapper.SysOperLogMapper;
import com.qlcx.system.service.ISysOperLogService;

/**
 * ActionNhật ký 服务层处理
 * 
 * @author qlcx
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService
{
    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * Thêm vàoActionNhật ký
     * 
     * @param operLog ActionNhật ký对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog)
    {
        operLogMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统ActionNhật ký集合
     * 
     * @param operLog ActionNhật ký对象
     * @return ActionNhật ký集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog)
    {
        return operLogMapper.selectOperLogList(operLog);
    }

    /**
     * 批量Xóa系统ActionNhật ký
     * 
     * @param ids 需要Xóa的数据
     * @return
     */
    @Override
    public int deleteOperLogByIds(String ids)
    {
        return operLogMapper.deleteOperLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询ActionNhật ký详细
     * 
     * @param operId ActionID
     * @return ActionNhật ký对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId)
    {
        return operLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空ActionNhật ký
     */
    @Override
    public void cleanOperLog()
    {
        operLogMapper.cleanOperLog();
    }
}
