package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysLogininfor;

/**
 * System access log information data layer
 */
public interface SysLogininforMapper {
  /**
   * Added system login log
   *
   * @param logininfor access log object
   */
  public void insertLogininfor(SysLogininfor logininfor);

  /**
   * Query system login log collection
   *
   * @param logininfor access log object
   * @return login record collection
   */
  public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

  /**
   * Delete system login logs in batch
   *
   * @param ids data to be deleted
   * @return result
   */
  public int deleteLogininforByIds(String[] ids);

  /**
   * Clear system login log
   *
   * @return result
   */
  public int cleanLogininfor();
}