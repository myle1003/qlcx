package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysOperLog;

/**
 * Operation log data layer
 */
public interface SysOperLogMapper {
  /**
   * New operation log
   *
   * @param operLog operation log object
   */
  public void insertOperlog(SysOperLog operLog);

  /**
   * Query system operation log collection
   *
   * @param operLog operation log object
   * @return operation log collection
   */
  public List<SysOperLog> selectOperLogList(SysOperLog operLog);

  /**
   * Batch delete system operation logs
   *
   * @param ids data to be deleted
   * @return result
   */
  public int deleteOperLogByIds(String[] ids);

  /**
   * Check operation log details
   *
   * @param operId operation ID
   * @return operation log object
   */
  public SysOperLog selectOperLogById(Long operId);

  /**
   * Clear operation log
   */
  public void cleanOperLog();
}